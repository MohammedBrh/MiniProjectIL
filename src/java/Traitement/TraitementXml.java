/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Traitement;

/**
 *
 * @author Mohammed
 */
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TraitementXml {

    public static void main(String argv[]) {
        List<String> diaclitise = new ArrayList();
        diaclitise.add("َ");
        diaclitise.add("ً");
        diaclitise.add("ُ");
        diaclitise.add("ٌ");
        diaclitise.add("ّ");
        diaclitise.add("ْ");
        diaclitise.add("ِ");
        diaclitise.add("ٍ");

        try {
//creating a constructor of file class and parsing an XML file  
            File file = new File("src//java//Ressource//SampleOutputFile.xml");
//an instance of factory that gives a document builder  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//an instance of builder to parse the specified xml file  
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("word");
// nodeList is not iterable, so we are using for loop  
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);

                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    NodeList nodeListSE = eElement.getElementsByTagName("morph_feature_set");
                    NodeList nodeListTok = eElement.getElementsByTagName("tokenized");
                    Element eElementDi = (Element) nodeListSE.item(0);

                    Node NOdeTook = nodeListTok.item(3);
                    Element eElementTok = (Element) NOdeTook;
                    NodeList nodeListTokFor = eElementTok.getElementsByTagName("tok");

                    String MotDiaclitise = normalize(eElementDi.getAttribute("diac"));
                    if (MotDiaclitise.isEmpty()) {
                        continue;
                    }
                    char[] caracterDiac = MotDiaclitise.toCharArray();

                    System.out.println("Diaclitise       :" + MotDiaclitise);
                    String MotDiaclitiseTrait = MotDiaclitise;
                    String Stem = "";
                    if (nodeListTokFor.getLength() <= 1) {
                        char[] caracterMot = MotDiaclitise.toCharArray();
                        List<String> carrMot = ConvertToList(caracterMot);
                        int LastDiaclitise = 0;
                        if (diaclitise.contains(carrMot.get(carrMot.size() - 1))) {
                            LastDiaclitise++;
                        }
                        if (diaclitise.contains(carrMot.get(carrMot.size() - 1)) && diaclitise.contains(carrMot.get(carrMot.size() - 2))) {
                            LastDiaclitise++;
                        }
                        for (int i = 0; i < carrMot.size() - LastDiaclitise; i++) {
                            Stem = Stem.concat(carrMot.get(i) + "");
                            System.out.println(carrMot.get(i) +"|" );
                        }

                    } else {
                        for (int itrTok = 0; itrTok < nodeListTokFor.getLength(); itrTok++) {
                            Node nodeTok = nodeListTokFor.item(itrTok);
                            Element eElementTokK = (Element) nodeTok;
                            String Formm = eElementTokK.getAttribute("form0");
                            char[] caracterFormm = Formm.toCharArray();
                            List<String> carr = ConvertToList(caracterFormm);

                            if (carr.get(0).equals("+")) {
//                                if (!carr.get(0).equals('+')) {
//                                    String enC = "";
//                                    for (int j = 1; j < carr.size(); j = j + 2) {
//                                        enC += caracterDiac[caracterDiac.length - j];
//                                    }
                                MotDiaclitiseTrait = RemoveEnclitique(Formm, MotDiaclitiseTrait, diaclitise);
//                                System.out.println("Steem == " + MotDiaclitiseTrait);
//                                }
                            } else if (carr.get(carr.size() - 1).equals("+")) {
                                MotDiaclitiseTrait = RemoveProclitique(Formm, MotDiaclitiseTrait, diaclitise);
//                                System.out.println("Steem == " + MotDiaclitiseTrait);
                            }
                        }
                        Stem = MotDiaclitiseTrait;

                    }

                    System.out.println("Steem == " + Stem);
//                    for (int i = 0; i < caracterDiac.length; i++) {
//                        System.out.println(caracterDiac[i]);
//                    }
//                    System.out.println("Student id: " + eElement.getElementsByTagName("id").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String normalize(String linee) {

        String line = linee.replaceAll("\\s+", " ");
        line = line.replaceAll("\\p{Punct}|[a-zA-Z]|[0-9]", "");
        line = line.replaceAll("(.)\\1{1,}", "$1");
        return line;
    }

    public static String RemoveProclitique(String Encl, String Mot, List<String> diaclitise) {
        char[] caracterProc = Encl.toCharArray();
        char[] caracterMot = Mot.toCharArray();

        List<String> carrProc = ConvertToList(caracterProc);
        List<String> carrMot = ConvertToList(caracterMot);
        carrProc.remove("+");

        while (!carrProc.isEmpty()) {
            String mm = carrProc.get(0);
            int index = Mot.indexOf(mm);
            carrProc.remove(0);
            for (int i = 0; i <= index; i++) {
                carrMot.remove(0);
            }

            String Res = "";
            for (int i = 0; i < carrMot.size(); i++) {
                Res = Res.concat(carrMot.get(i) + "");
            }
            Mot = Res;
        }
        String Res = "";
        int LastDiaclitise = 0,DepuSDiaclitise = 0;
        if (diaclitise.contains(carrMot.get(0))) {
            DepuSDiaclitise++;
        }
        if (diaclitise.contains(carrMot.get(0)) && diaclitise.contains(carrMot.get(1))) {
            DepuSDiaclitise++;
        }
        if (diaclitise.contains(carrMot.get(carrMot.size() - 1))) {
            LastDiaclitise++;
        }
        if (diaclitise.contains(carrMot.get(carrMot.size() - 1)) && diaclitise.contains(carrMot.get(carrMot.size() - 2))) {
            LastDiaclitise++;
        }
        for (int i = DepuSDiaclitise; i < carrMot.size() - LastDiaclitise; i++) {
            Res = Res.concat(carrMot.get(i) + "");
            System.out.println(carrMot.get(i) + "#");
        }
        return Res;

    }

    public static String RemoveEnclitique(String Proc, String Mot, List<String> diaclitise) {
        char[] caracterProc = Proc.toCharArray();
        char[] caracterMot = Mot.toCharArray();

        List<String> carrProc = ConvertToList(caracterProc);
        List<String> carrMot = ConvertToList(caracterMot);
        carrProc.remove("+");

        while (!carrProc.isEmpty()) {
            int kInd = carrProc.size() - 1;
            String mm = carrProc.get(kInd);
            int index = Mot.lastIndexOf(mm);
            carrProc.remove(kInd);
            for (int i = carrMot.size() - 1; i >= index; i--) {
                carrMot.remove(i);
            }

            String Res = "";
            for (int i = 0; i < carrMot.size(); i++) {
                Res = Res.concat(carrMot.get(i) + "");
            }
        }
        String Res = "";
        int LastDiaclitise = 0;
        if (diaclitise.contains(carrMot.get(carrMot.size() - 1))) {
            LastDiaclitise++;
        }
        if (diaclitise.contains(carrMot.get(carrMot.size() - 1)) && diaclitise.contains(carrMot.get(carrMot.size() - 2))) {
            LastDiaclitise++;
        }

        for (int i = 0; i < carrMot.size() - LastDiaclitise; i++) {
            Res = Res.concat(carrMot.get(i) + "");
            System.out.println(carrMot.get(i) + "#");
        }
        return Res;

    }

    public static List ConvertToList(char[] caracterProc) {
        List<String> res = new ArrayList();
        for (int i = 0; i < caracterProc.length; i++) {
            res.add(caracterProc[i] + "");
        }
        return res;
    }

}
