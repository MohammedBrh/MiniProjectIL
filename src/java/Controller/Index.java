package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Traitement.TraitementXml;
import com.sun.org.apache.xml.internal.serializer.Encodings;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mohammed
 */
public class Index extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
        requestDispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Sent = request.getParameter("Tarai");
//        request.setCharacterEncoding("UTF-8");
        System.out.println(Sent);
        List<Integer> sb = new ArrayList<>();
        for (char c : Sent.toCharArray()) {
            sb.add((int) c);
        }
        sb.forEach((ii) -> {
            System.out.println((int) ii + " --");
        });
        CreeInput(sb);
        // store ascii integer string array in large integer
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\">");
            out.println("<title>Servlet NewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            char[] b = Sent.toCharArray();
//            Traitement.TraitementXml t = new TraitementXml(b, "G:\\wwssservice\\MIni_Projet_IL\\src\\java\\Ressource\\SampleInputFile.xml", "G:\\wwssservice\\MIni_Projet_IL\\src\\java\\Ressource\\SampleInputFile.xml");
            for (char n : b) {
                String text = Integer.toBinaryString(n);
                System.out.println(n + "  ==  " +text);
            }
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            out.println("<p dir=\"rtl\" >" + Sent + "</p>");
            System.out.println(new String(Sent.getBytes(), "UTF-8"));
            out.println(new String(Sent.getBytes(), "utf-8"));
            out.println("</body>");
            out.println("</html>");
        }
////        PrintWriter writer = new PrintWriter(System.out);
//        String myfile = getServletContext().getRealPath("tes.txt");
////        String myfile = getServletContext().getRealPath("SampleInputFile.xml");
//        String myfileOut = getServletContext().getRealPath("SampleOutputFile.xml");
//        System.out.println("File :" + myfile);
//        TraitementXml t = new TraitementXml(Sent, "G:\\wwssservice\\MIni_Projet_IL\\src\\java\\Ressource\\SampleInputFile.xml", "G:\\wwssservice\\MIni_Projet_IL\\web\\SampleOutputFile.xml");

    }

    public void CreeInput(List<Integer> inn) {
        String Header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<!--\n"
                + "  ~ Copyright (c) 2013. The Trustees of Columbia University in the City of New York.\n"
                + "  ~ The copyright owner has no objection to the reproduction of this work by anyone for\n"
                + "  ~ non-commercial use, but otherwise reserves all rights whatsoever.  For avoidance of\n"
                + "  ~ doubt, this work may not be reproduced, or modified, in whole or in part, for commercial\n"
                + "  ~ use without the prior written consent of the copyright owner.\n"
                + "  -->\n"
                + "\n"
                + "<madamira_input xmlns=\"urn:edu.columbia.ccls.madamira.configuration:0.1\">\n"
                + "    <madamira_configuration>\n"
                + "        <preprocessing sentence_ids=\"false\" separate_punct=\"true\" input_encoding=\"UTF8\"/>\n"
                + "        <overall_vars output_encoding=\"UTF8\" dialect=\"MSA\" output_analyses=\"TOP\" morph_backoff=\"NONE\"/>\n"
                + "        <requested_output>\n"
                + "            <req_variable name=\"PREPROCESSED\" value=\"true\" />\n"
                + "            <req_variable name=\"STEM\" value=\"true\" />\n"
                + "            <req_variable name=\"GLOSS\" value=\"false\" />\n"
                + "            <req_variable name=\"LEMMA\" value=\"false\" />\n"
                + "            <req_variable name=\"DIAC\" value=\"true\" />\n"
                + "            <req_variable name=\"ASP\" value=\"true\" />\n"
                + "            <req_variable name=\"CAS\" value=\"true\" />\n"
                + "            <req_variable name=\"ENC0\" value=\"true\" />\n"
                + "            <req_variable name=\"ENC1\" value=\"false\" />\n"
                + "            <req_variable name=\"ENC2\" value=\"false\" />\n"
                + "            <req_variable name=\"GEN\" value=\"true\" />\n"
                + "            <req_variable   name=\"MOD\" value=\"true\" />\n"
                + "            <req_variable name=\"NUM\" value=\"true\" />\n"
                + "            <req_variable name=\"PER\" value=\"true\" />\n"
                + "            <req_variable name=\"POS\" value=\"true\" />\n"
                + "            <req_variable name=\"PRC0\" value=\"true\" />\n"
                + "            <req_variable name=\"PRC1\" value=\"true\" />\n"
                + "            <req_variable name=\"PRC2\" value=\"true\" />\n"
                + "            <req_variable name=\"PRC3\" value=\"true\" />\n"
                + "            <req_variable name=\"STT\" value=\"true\" />\n"
                + "            <req_variable name=\"VOX\" value=\"true\" />\n"
                + "            <req_variable name=\"BW\" value=\"false\" />\n"
                + "            <req_variable name=\"SOURCE\" value=\"false\" />\n"
                + "			<req_variable name=\"NER\" value=\"true\" />\n"
                + "			<req_variable name=\"BPC\" value=\"true\" />\n"
                + "        </requested_output>\n"
                + "        <tokenization>\n"
                + "            <scheme alias=\"ATB\" />\n"
                + "			<scheme alias=\"D3_BWPOS\" /> <!-- Required for NER -->\n"
                + "            <scheme alias=\"ATB4MT\" />\n"
                + "            <scheme alias=\"MyD3\">\n"
                + "				<!-- Same as D3 -->\n"
                + "				<scheme_override alias=\"MyD3\"\n"
                + "								 form_delimiter=\"\\u00B7\"\n"
                + "								 include_non_arabic=\"true\"\n"
                + "								 mark_no_analysis=\"false\"\n"
                + "								 token_delimiter=\" \"\n"
                + "								 tokenize_from_BW=\"false\">\n"
                + "					<split_term_spec term=\"PRC3\"/>\n"
                + "					<split_term_spec term=\"PRC2\"/>\n"
                + "					<split_term_spec term=\"PART\"/>\n"
                + "					<split_term_spec term=\"PRC0\"/>\n"
                + "					<split_term_spec term=\"REST\"/>\n"
                + "					<split_term_spec term=\"ENC0\"/>\n"
                + "					<token_form_spec enclitic_mark=\"+\"\n"
                + "									 proclitic_mark=\"+\"\n"
                + "									 token_form_base=\"WORD\"\n"
                + "									 transliteration=\"UTF8\">\n"
                + "						<normalization type=\"ALEF\"/>\n"
                + "						<normalization type=\"YAA\"/>\n"
                + "						<normalization type=\"DIAC\"/>\n"
                + "						<normalization type=\"LEFTPAREN\"/>\n"
                + "						<normalization type=\"RIGHTPAREN\"/>\n"
                + "					</token_form_spec>\n"
                + "				</scheme_override>\n"
                + "			</scheme>\n"
                + "        </tokenization>\n"
                + "    </madamira_configuration>\n"
                + "\n"
                + "\n"
                + "    <in_doc id=\"ExampleDocument\">\n"
                + "        <in_seg id=\"SENT1\">";

        String footer = "</in_seg>\n"
                + "       \n"
                + "    </in_doc>\n"
                + "\n"
                + "</madamira_input>\n"
                + "";
        try {
            try (//            FileInputStream fstream =                 new FileInputStream(getServletContext().getRealPath("/database.txt"));
                    //            FileWriter fw = new FileWriter(getServletContext().getRealPath("\\src\\java\\Ressource")+"\\SampleInputFile.xml");
                    FileWriter fw = new FileWriter("G:\\wwssservice\\MIni_Projet_IL\\src\\java\\Ressource\\SampleInputFile.xml")) {
                fw.write(Header);
                String str = "";
                for (int c : inn) {
                    str += new Character((char) c).toString();
                }
                System.out.println(str);
                fw.write(str);

                fw.write(footer);
            }
            System.out.println("Le texte a été écrit avec succès");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
