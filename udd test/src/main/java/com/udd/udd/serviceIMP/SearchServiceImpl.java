package com.udd.udd.serviceIMP;

import com.itextpdf.text.pdf.PdfDocument;
import com.udd.udd.ElasticSearchRepository.WorkESRepository;
import com.udd.udd.model.CoAuthor;
import com.udd.udd.model.Work;
import com.udd.udd.modelES.WorkES;
import com.udd.udd.service.SearchService;


import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@SuppressWarnings("Duplicates")
public class SearchServiceImpl implements SearchService {

    @Autowired
    WorkESRepository workESRepository;

    @Override
    public void makeDOI(Work work) {
        WorkES workES=new WorkES();
        workES.setId(work.getId());
        workES.setKeyTerms(work.getKeyTerms());
        workES.setMagazineName(work.getMagazine().getName());
        workES.setScienceArea(work.getScienceArea().getId());
        workES.setWorkAbstract(work.getWorkAbstract());
        workES.setStatus("approved");
        workES.setTitle(work.getTitle());

        String authors="";
        authors+=work.getAuthor().getName()+" "+work.getAuthor().getSurname();
        for(CoAuthor coAuthor:work.getCoAuthors()){
            authors+=" ,"+coAuthor.getName()+" "+coAuthor.getSurname();
        }

        workES.setAuthors(authors);

        String[] path = work.getProposalWorkPath().split("/");
        String fileName = path[path.length - 1];

        File file = new File(work.getProposalWorkPath());
        String parsedText="";
        try {
//            PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
//            parser.parse();
//
//            //pdDoc = PDDocument.load(new File(fileName)); pdfStripper = new PDFTextStripper();
//            COSDocument cosDoc = parser.getDocument();
            PDFTextStripper pdfStripper = new PDFTextStripper();
            PDDocument pdDoc = PDDocument.load(file);
            parsedText = pdfStripper.getText(pdDoc);
        }catch (Exception e){
            e.printStackTrace();
        }
        workES.setWorkContent(parsedText);
        this.workESRepository.save(workES);
    }
}
