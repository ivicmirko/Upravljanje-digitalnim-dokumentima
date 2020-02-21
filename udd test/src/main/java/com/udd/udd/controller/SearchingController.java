package com.udd.udd.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.udd.udd.ElasticSearchRepository.ReviewerESRepository;
import com.udd.udd.ElasticSearchRepository.WorkESRepository;
import com.udd.udd.dto.ChoseReviewersDTO;
import com.udd.udd.model.*;
import com.udd.udd.modelES.ReviewerES;
import com.udd.udd.modelES.WorkES;

import com.udd.udd.repository.ScienceAreaRepository;
import com.udd.udd.repository.WorkRepository;
import com.udd.udd.searchDTO.WorkESdto;
import com.udd.udd.service.SystemUserService;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.http.protocol.HTTP;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/search")
@SuppressWarnings("Duplicates")
public class SearchingController {


    @Autowired
    WorkESRepository workESRepository;

    @Autowired
    WorkRepository workRepository;

    @Autowired
    ScienceAreaRepository scienceAreaRepository;

    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SystemUserService systemUserService;

    @Autowired
    ReviewerESRepository reviewerESRepository;

    @GetMapping(path="/fix123")
    public @ResponseBody ResponseEntity fix123(){

        WorkES workES=this.workESRepository.findById(1);
//        String text=workES.getWorkContent();
//        text="Frekventni regulatori su elektronski uređaji koji omogućavaju upravljanje brzinom trofaznih asinhronih motora pretvarajući ulazni mrežni napon i frekvenciju, koji su fiksirane vrednosti, u promenljive veličine.Frekventni regulator je termin koji se odomaćio kod nas. Postoji mnoštvo naziva za ove uređaje u engleskoj terminologiji."; // kao što su Adjustable Speed Drives, Variable Frequency Drives (VFD),  Inverter  itd.  Pored osnovne funkcije \r\nupravljanja brzinom AC motora,  frekventni regulatori integrišu i brojne druge \r\nfunkcionalnosti kao što su:  zaštita motora,  alarmiranje,  procesno upravljanje u \r\nzatvorenoj petlji (na primer održavanje konstantnog pritiska u cevi),  mogućnosti \r\npodešavanja brzine i kontrola rada putem raznih interfejsa (ručno preko tastera \r\nna samom regulatoru ili daljinski povezivanjem na komunikacione interfejse kao \r\nšto su RS485 MODBUS, PROFIBUS, itd.).\r\nNa  slici 1.1   je  prikazana  interna  struktura  frekventnog  regulatora.  Ispravljač \r\npretvara mrežni AC napon u pulsirajući DC napon. Međukolo stabiliše ovaj DC \r\nnapon  i  stavlja  ga  na  raspolaganje  invertoru.  Invertor  generiše  frekvenciju \r\nnapona  na  motoru  (DC  napon  ponovo  pretvara  u  kontrolisani  AC  napon). \r\nUpravljačko kolo prima i šalje signale iz ispravljača, međukola i invertora. To je \r\nmikroprocesorski  sistem koji  na  osnovu  svojih  algoritama upravljanja  definiše \r\npobudu za motor kako bi se dobio željeni odziv.\r\nSlika 1.1\r\nZbog sve većeg učešća automatike u industriji,  postoji  konstantna potreba za \r\nautomatskim upravljanjem,  a neprekidno povećanje  brzine  proizvodnje  i  bolje \r\nmetode  za  poboljšanje  stepena  korisnosti  pogona  se  stalno  razvijaju  i \r\n3\r\nMomentum doo   Tel/Fax: 022 625 010 • Mob.: 062 252 818 • 065 2622 066 • Email: momentum@eunet.rs\r\nunapređuju. Elektromotori su danas važan standardan industrijski proizvod. Sve \r\ndok se nisu pojavili frekventni  regulatori  nije bilo moguće u potpunosti upravljati \r\nbrzinom trofaznog AC motora.\r\nPored pune kontrole brzine AC motora, korišćenje frekventnog regulatora nudi i \r\nbrojne druge prednosti:\r\n- Ušteda  energije je  pogotovo  u  današnje  vreme  jedan  od  prioritetnih \r\nzahteva. Ovo se pre svega odnosi na pogone sa pumpama i ventilatorima, \r\ngde  je  utrošak  energije  srazmeran  trećem stepenu  brzine.  Na  primer, \r\npogon koji radi sa polovinom brzine troši samo 12.5% nominalne snage.\r\n- Podešavanje  brzine  u  procesu  proizvodnje  pruža  brojne  prednosti  u \r\npogledu povećanja produktivnosti, smanjenja troškova održavanja, itd.\r\n- Broj startovanja i zaustavljanja mašine može se punom kontrolom brzine \r\ndrastično  smanjiti.  Korišćenjem  laganog  ubrzavanja  i  usporavanja, \r\nizbegavaju se naprezanja i nagli udari u mašinskim sklopovima.\r\n- Uz smanjenje troškova održavanja, poboljšava se radno okruženje.\r\nKao što je rečeno, frekventni regulatori kontrolišu brzinu rada motora menjanjem \r\nfrekvencije  napona motora.  Sinhrona brzina (brzina obrtanja magnetnog polja \r\nstatora u rotacijama po minuti, RPM) iznosi:\r\nMotoraBrojPolova\r\naFrekvencijzinaSinhronaBr *120=\r\nNominalna brzina obrtanja motora predstavlja brzinu obrtanja osovine motora sa \r\nnominalnim opterećenjem i pri nominalnoj frekvenciji napona napajanja (50 Hz). \r\nOva brzina je nešto manja od sinhrone brzine, a razlika se naziva klizanje (slip), i \r\nuslov je za stvaranje obrtnog momenta. Na primer, sinhrona bzina četvoropolnog \r\nmotora je 1500 RPM, a nominalna brzina motora može biti 1460 RPM. Dakle, \r\nmenjanjem frekvencije, može se menjati brzina motora.\r\nFrekventni regulator kontroliše zajedno izlaznu frekvenciju i  napon prema slici \r\n1.2, održavajući konstantan odnos napon/frekvencija (volt/hertz). Momenat koji \r\nse stvara je direktno srazmeran ovom odnosu, što znači da je na svim brzinama \r\n(do nominalne brzine) momenat konstantan i jednak je  nominalnom  momentu. \r\nOvo znači da motor na svim brzinama može da isporuči pun momenat.\r\nRegulator može da napaja motor i sa frekvencijama iznad nominalne (50 ili 60 \r\nHz), ali u tom slučaju nije moguće dalje povećavanje napona. U tom slučaju se \r\nmomenat smanjuje, pa postoji mogućnost da motor na većim brzinama ne može \r\nda isporuči dovoljan momenat za pokretanje datog opterećenja.\r\n4\r\nMomentum doo   Tel/Fax: 022 625 010 • Mob.: 062 252 818 • 065 2622 066 • Email: momentum@eunet.rs\r\nSlika 1.2\r\n2. Prednosti korišćenja frekventnih regulatora\r\nVentilatori,  pumpe  i  kompresori  se  često  koriste  bez  kontrole  brzine.  U  tom \r\nslučaju protok se reguliše sa ventilima ili prigušivanjem na druge načine. Kada se \r\nprotok kontroliše bez regulacije brzine, motor radi sa punom brzinom. Sistemi \r\ngrejanja, hlađenja i ventilacije (HVAC) retko zahtevaju maksimalan protok, već \r\non zavisi od brojnih faktora, kao što su npr. spoljna temperatura, itd. Upotrebom \r\nventila, prigušivača i ventilatorskih demfera prigušuje se protok, i sistem tokom \r\nnajvećeg dela vremena bespotrebno troši  energiju.  Ovo se može uporediti  sa \r\nautomobilom, kada dajemo pun gas i pritiskamo kočnicu kako bi smanjili brzinu. \r\nKorišćenje frekventnog regulatora za kontrolisanje brzine motora može uštedeti i \r\ndo 70% energije.\r\nSlika 2.1\r\n5\r\nMomentum doo   Tel/Fax: 022 625 010 • Mob.: 062 252 818 • 065 2622 066 • Email: momentum@eunet.rs\r\nPumpe i ventilatori sa frekventnim regulatorom\r\nNa slici 2.2 su prikazane krive karakteristika pumpe (ventilatora) i samog sistema \r\nu kojem pumpa postoji. \r\nKriva pumpe predstavlja zavisnost pritiska koji daje pumpa H (koji je izražen u \r\nvisini vodenog stuba koji pumpa može da savlada – engl. Head, vrh) od protoka \r\nQ. Kriva pokazuje da pumpa može da daje manji protok ukoliko treba da savlada \r\nveću razliku pritiska u sistemu.\r\nKriva  sistema se sastoji  od  dva dela.  Pritisak potreban pri  nultom protoku je \r\nstatički pritisak. On predstavlja visinu vodenog stuba koji pumpa u sistemu može \r\nda  savlada  bez  obzira  na  protok.  Ili,  može  se  shvatiti  kao  rad  koji  pumpa \r\nsavladava  nasuprot  gravitaciji.  Druga  komponenta  predstavlja  meru  otpora \r\nsamog sistema (cevi, ventilacioni otvori), i povećava se sa povećanjem protoka. \r\nPresek krivi karakteristike pumpe i sistema predstavlja radnu tačku. Upotrebom \r\nfrekventnog  regulatora  (VFD)  i  smanjivanjem  brzine  obrtanja  pumpe, \r\nkarakteristika pumpe se spušta ispod. Upotrebom ventila (prigušnica), menja se \r\nkarakteristika sistema, a ne pumpe. Za postizanje istog protoka, pritisak pumpe \r\nje manji kod upotrebe VFD uređaja. Kako je ulazna snaga srazmerna proizvodu \r\nprotoka i pritiska, zaključuje se da se korišćenjem VFD uređaja štedi energija.\r\nSlika 2.2\r\n6\r\nMomentum doo   Tel/Fax: 022 625 010 • Mob.: 062 252 818 • 065 2622 066 • Email: momentum@eunet.rs\r\nUšteda energije\r\nOdnosi između pritiska, protoka, brzine obrtanja osovine, snage se mogu izraziti \r\nzakonima afiniteta (Slika 2.3). Protok je direktno proporcionalan sa brzinom, dok \r\nje  pritisak  proporcionalan  kvadratu  brzine.  Sa  stanovišta  uštede  energije, \r\nnajznačajnije  je  to  što  je  snaga  koja  se  troši  proporcionalna  trećem stepenu \r\nbrzine. Tako, na primer, 75% brzine proizvodi  75% protoka, ali  se troši  samo \r\n42% od  snage  neophodne  za  puni  protok.  Kada  se  protok  smanji  na  50%, \r\nutrošak snage je svega 12.5%.\r\nSlika 2.3\r\nKlasične metode kontrole protoka obuhvataju primenu ventila, ulaznih lopatica \r\nkod  centrifugalnih  ventilatora  za  smanjenje  protoka  vazduha  koji  ulazi  u \r\nventilator, on/off kontrola, itd. Glavni problem kod ovih načina kontrole protoka je \r\nto  što  se  ne  utiče  na  potrošnju.  Postoje  mogućnosti  za  smanjenje  utroška \r\nenergije, ali nijedna nije efikasna kao upotreba frekventnih regulatora. Na primer, \r\nOn/Off  kontrola  stvara  mehaničke  udare  i  pikove  pritiska  usled  konstantnog \r\nuključivanja i isključivanja, kao i pikove struje u mreži napajanja kada se motor \r\nuključuje direktno bez primene regulatora.\r\nUpotrebom frekventnih regulatora postižu se velike uštede u kontroli procesa gde \r\nsu opterećenje ili brzina promenjivi.  Naročite uštede se postižu kod kontrole \r\nopterećenja koja imaju promenjivi momenat opterećenja u zavisnosti od brzine, a \r\nto su upravo  pumpe,  ventilatori,  kompresori,  itd. Na slici 2.4 je prikazana kriva \r\nmomenta (Torque) i  Snage (HP) u zavisnosti  od brzine, a na slici 2.5 je dato \r\npoređenje  utroška  energije  između  kontrole  protoka  ventilom  (ili  drugim \r\nprigušivačem) i upotrebom frekventnog regulatora.\r\n7\r\nMomentum doo   Tel/Fax: 022 625 010 • Mob.: 062 252 818 • 065 2622 066 • Email: momentum@eunet.rs\r\nSlika 2.4\r\nSlika 2.5\r\nCelokupan HVAC sistem se dizajnira prema najvećim zahtevanim vrednostima \r\nprocesnih promenjivih (protok, pritisak, temperatura…). Ovo znači da su pumpe \r\nili  ventilatori  predimenzionisani tokom najvećeg dela radnog vremena. Na slici  \r\n2.6  je  prikazan tipičan radni  ciklus  pumpe ili  ventilatora.  Tokom 90% radnog \r\nvremena zahtevani protok je ispod 70%. Kontrolisanjem brzine motora pumpe ili  \r\nventilatora mogu se postići značajne uštede energije.\r\n8\r\nMomentum doo   Tel/Fax: 022 625 010 • Mob.: 062 252 818 • 065 2622 066 • Email: momentum@eunet.rs\r\nSlika 2.6\r\nSlika 2.7 prikazuje odnos troškova tokom životnog ciklusa pumpi i  ventilatora. \r\nNajveći deo troškova odlazi na utrošak energije tokom rada. Uštedom energije \r\nkoju  omogućavaju  frekventni  regulatori  vrlo  brzo  se  nadoknađuju  nešto  veće \r\ninvesticije  potrebne  za  instaliranje  sistema.  Takođe,  upotrebom  frekventnih \r\nregulatora cena održavanja se smanjuje, štiti  se motor i  produžava se njegov \r\nživotni vek. \r\nSlika 2.7\r\n9\r\nMomentum doo   Tel/Fax: 022 625 010 • Mob.: 062 252 818 • 065 2622 066 • Email: momentum@eunet.rs\r\nPoboljšana kontrola procesa\r\nUpotreba  frekventnih  regulatora  značajno  poboljšava  proces  proizvodnje.  Na \r\nprimer, procesi ekstrudiranja gume ili  obrade metala zavise od brojnih faktora \r\nkao što su karakteristika materijala, poprečni presek, temperatura… Ukoliko se \r\nkoriste  pokretne  trake  sa  konstantnom  brzinom  i  menja  se  temperatura \r\nneophodna za dati proces, stvaraće se škart proizvod, ili će se morati zaustavljati \r\nproces proizvodnje kako bi se temperatura dovela na neophodnu vrednost. U \r\noba  slučaja  se  nepotrebno  troše  energija,  vreme  i  materijal.  Umesto  toga \r\npromenom  brzine  trake  sa  materijalom  lako  se  kompenzuju  promene \r\ntemperature čime se omogućava kontinualni rad. Rezultat je smanjenje utroška \r\nenergije i škarta proizvoda.\r\nFrekventni  regulatori  omogućavaju  precizniju  kontrolu  hemijskih  procesa, \r\nprocesa distribucije vode, ventilacije, itd. Pritisak u sistemima distribucije vode se \r\nmože  održavati  sa  znatno  boljom  tolerancijom.  Na  slici  2.8 je  prikazan \r\njednostavan  sistem  za  održavanje  konstantnog  pritiska  u  cevi.  Sistem  je  u \r\nzatvorenoj  sprezi.  Željena  referentna  vrednost  pritiska  se  podešava  na \r\nregulatoru.  VFD  uređaj  dobija  povratnu  informaciju  sa  senzora  protoka  (ili  \r\npritiska), koji  se povezuje na analogne ulaze regulatora. PID kontroler,  koji  je \r\nsastavni  deo  regulatora  reguliše  brzinu  motora  pumpe  kako  bi  se  održavao \r\nkonstantni pritisak. Ova brzina zavisi dominantno od trenutne potrošnje, itd.  \r\nSlika 2.8\r\nSmanjenje udara\r\nSledeća prednost frekventnog regulatora se ogleda u smanjenju mehaničkih i \r\nelektričnih udara prilikom  uključenja/isključenja  mašine.  Frekventni  regulatori \r\n10\r\nMomentum doo   Tel/Fax: 022 625 010 • Mob.: 062 252 818 • 065 2622 066 • Email: momentum@eunet.rs\r\nomogućavaju  mekan  start  mašine,  postepenim  povećavanjem  frekvencije  po \r\nprogramiranoj  rampi.  Direktnim  uključenjem  motora  na  mrežu  trenutna  struja \r\nmože imati vrednost i do deset puta veću od nominalne  struje In. Mekan start \r\nfrekventnog regulatora ograničava ovu struju  na vrednost  ne  veću od 1.5  In. \r\nOvim se produžava životni vek motora i smanjuju troškovi održavanja. Za pogone \r\nkod kojih čak ni linearna rampa ubrzanja i zaustavljanja ne rešavaju u potpunosti \r\nproblem  trzaja  kod  pokretanja  (npr.liftovi)  frekventni  regulatori  nude  varijantu \r\neksponencijalne  rampe  ili  tzv.  S  rampe  koja  dalje  poboljšava  prilike  kod \r\nstartovanja i zaustavljanja elektromotornog pogona.\r\nIz ponude naših frekventnih regulatora izdvajamo:\r\nSINUS PENTA, 5 funkcija, frekventni regulator za sve namene, do \r\n3000kW\r\n• Širok opseg napona napajanja, 200Vac-690Vac \r\n• Opseg DC napona napajanja 280-970 Vdc \r\n• Ulazna frekvencija 50-60Hz \r\n• Opseg snage 1,3-3000kW \r\n• Kućište IP00, IP20, IP54 \r\n• Verzija za samostalnu ugradnju ili rešenje u ormanu (cabinet solution)\r\n• kompatibilnost sa REMOTE DRIVE softverom koji omogućava povezivanje, kontrolu, \r\nprogramiranje i monitoring, lokalno ili putem interneta. \r\n• kompatibilnost sa softverom Easy Harmonics, alatom za proračun viših harmonika. \r\nSINUS N, jeftini frekventni regulator, Open loop vektor ili V/F \r\ninverter \r\n• 200-230Vac monofazno napajanje \r\n• Opseg snage 0,4-3kW \r\n• Potpuna kompatibilnost sa softverom za kontrolu i programiranje REMOTE DRIVE \r\n• 200-230Vac monofazno napajanje \r\n• Opseg snage 0,4-3kW \r\n• 2 moda kontrole: V/F i open loop vektorska kontrola (Sensorless Vector Control) \r\n• Ugrađeni potenciometar \r\n• Start/Stop tasteri \r\n• Stepen zaštite IP20 \r\nSINUS M, frekventni regulator velikih mogućnosti, Open loop vektor ili V/F \r\ninverter \r\n• 200-480Vac napajanje \r\n• opseg snage 0,37-22kW \r\n• Potpuna kompatibilnost sa softverom za kontrolu i programiranje REMOTE DRIVE \r\n• potpuna kompatibilnost sa Easy harmonics programskim alatom za računanje viših \r\nharmonika\r\n11\r\nMomentum doo   Tel/Fax: 022 625 010 • Mob.: 062 252 818 • 065 2622 066 • Email: momentum@eunet.rs\r\nviše o ovome na http://www.momentum-automation.com/\r\nKontakt osoba:\r\nBorislav Dugošija\r\nMomentum d.o.o.\r\nhttp://www.momentum-automation.com/\r\nKralja Petra I 48\r\n22000 Sremska Mitrovica\r\nTel/Fax: +381 22 625 010\r\nmomentum@eunet.rs\r\nbdugosija@momentum-automation.com\r\n12\r\n";
        File file = new File("C:/Users/Mirko/Desktop/MasterProjekat/proposalWorks/mujo/Frekventni regulatori.pdf");
        String parsedText="";
        try {

            PDFTextStripper pdfStripper = new PDFTextStripper();
            PDDocument pdDoc = PDDocument.load(file);
            parsedText = pdfStripper.getText(pdDoc);
        }catch (Exception e){
            e.printStackTrace();
        }
        parsedText=StringEscapeUtils.escapeJson(parsedText);
        workES.setWorkContent(parsedText);
        workESRepository.save(workES);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = "/indexReviewers")
    public @ResponseBody ResponseEntity indexReviewers(){

        List<SystemUser> systemUsers=new ArrayList<>();
        systemUsers=this.systemUserService.findAllreviewers();
        for(SystemUser systemUser:systemUsers){
            ReviewerES reviewerES=new ReviewerES();
            reviewerES.setId(systemUser.getId());
            reviewerES.setReviewerId(systemUser.getId());
            reviewerES.setName(systemUser.getName()+" "+systemUser.getSurname());
            reviewerES.setLocation(new GeoPoint(Double.parseDouble(systemUser.getLatitude()),Double.parseDouble(systemUser.getLongitude())));
            this.reviewerESRepository.save(reviewerES);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path="/getMoreLikeThisReviewers/{workId}",
    produces = "application/json")
    @SuppressWarnings("Duplicates")
    public @ResponseBody ResponseEntity moreLikeThis(@PathVariable Long workId) throws  Exception{
        Set<ChoseReviewersDTO> retVal=new HashSet<>();
        Work work=workRepository.findOneById(workId);

        File file = new File(work.getProposalWorkPath());
        String parsedText="";
        try {

            PDFTextStripper pdfStripper = new PDFTextStripper();
            PDDocument pdDoc = PDDocument.load(file);
            parsedText = pdfStripper.getText(pdDoc);
        }catch (Exception e){
            e.printStackTrace();
        }

        parsedText= StringEscapeUtils.escapeJson(parsedText);

        String query="{\n" +
                "    \"query\": {\n" +
                "        \"more_like_this\" : {\n" +
                "            \"fields\" : [\"workContent\"],\n" +
                "            \"like\" : \""+parsedText+"\",\n" +
                "            \"min_term_freq\" : 1,\n" +
                "            \"max_query_terms\" : 12,\n" +
                "            \"min_doc_freq\": 1,\n" +
                "            \"analyzer\" : \"serbian\""+
                "        }\n" +
                "    }\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonquery = objectMapper.readTree(query);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<JsonNode> request = new HttpEntity<>(jsonquery);
        String fooResourceUrl
                = "http://localhost:9200/nc/work/_search?pretty";
        ResponseEntity<String> response
                = restTemplate.postForEntity(fooResourceUrl, request, String.class);
        JsonNode rootNode = objectMapper.readTree(response.getBody());
        JsonNode locatedNode = rootNode.path("hits").path("hits");
        List<WorkESdto> works = getRetVal(locatedNode, "workContent");
        for(WorkESdto dto:works){
            Work twork=this.workRepository.findOneById(dto.getId());
            for(ReviewerWork rw:twork.getReviewerWorks()){
                ChoseReviewersDTO choseReviewersDTO=new ChoseReviewersDTO();
                choseReviewersDTO.setId(rw.getSystemUser().getId());
                System.out.println("aaaaaaaa="+rw.getSystemUser().getName());
                choseReviewersDTO.setName(rw.getSystemUser().getName()+" "+rw.getSystemUser().getSurname());
                retVal.add(choseReviewersDTO);
            }
        }

        return new ResponseEntity(retVal, HttpStatus.OK);
    }

    @GetMapping(path = "/getReviewersByLocation/{workId}",
    produces = "application/json")
    @SuppressWarnings("Duplicates")
    public @ResponseBody ResponseEntity getReviewersByLocation(@PathVariable Long workId) throws Exception{

        Work work=workRepository.findOneById(workId);
        Author author=work.getAuthor();
        GeoPoint geoPoint=new GeoPoint(Double.parseDouble(author.getLatitude()),Double.parseDouble(author.getLongitude()));
        List<CoAuthor> coAuthors=work.getCoAuthors();
        Double lat=Double.parseDouble(author.getLatitude());
        Double lon=Double.parseDouble(author.getLongitude());
        System.out.println("A="+lat+" B="+lon);
        String query="{\n" +
                "    \"query\": {\n" +
                "        \"bool\" : {\n" +
                "            \"must\" : {\n" +
                "                \"match_all\" : {}\n" +
                "            },\n" +
                "            \"filter\" : {\n" +
                "            \"bool\" : {\n"+
                "               \"must_not\" : {\n"+
                "                \"geo_distance\" : {\n" +
                "                    \"distance\" : \"100km\",\n" +
                "                    \"location\" : {\n" +
                "                        \"lat\" :"+lat+",\n" +
                "                        \"lon\" :"+lon+"\n" +
                "                    }\n" +
                "                }\n" +
                "               }\n"+ //must not
                "              }\n"+
                "            }\n" +
                "        }\n" +
                "    }\n"+
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonquery = objectMapper.readTree(query);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<JsonNode> request = new HttpEntity<>(jsonquery);
        String fooResourceUrl
                = "http://localhost:9200/ncr/_doc/_search?pretty";
        ResponseEntity<String> response
                = restTemplate.postForEntity(fooResourceUrl, request, String.class);
        JsonNode rootNode = objectMapper.readTree(response.getBody());
        List<ChoseReviewersDTO> retVal = this.getReviewersFromResponse(rootNode);  //dobijem recenzente koji nisu u okolini autora
                //potrebno je izbaciti recenzente koji su u okolini koautora
        System.out.println("Imaaaaa="+retVal.size());
        for(CoAuthor coa:coAuthors){
            Double lat1=Double.parseDouble(coa.getLatitude());
            Double lon1=Double.parseDouble(coa.getLongitude());
            String query1="{\n" +
                    "    \"query\": {\n" +
                    "        \"bool\" : {\n" +
                    "            \"must\" : {\n" +
                    "                \"match_all\" : {}\n" +
                    "            },\n" +
                    "            \"filter\" : {\n" +
                    "                \"geo_distance\" : {\n" +
                    "                    \"distance\" : \"12km\",\n" +
                    "                    \"location\" : {\n" +
                    "                        \"lat\" : "+lat1+",\n" +
                    "                        \"lon\" : "+lon1+"\n" +
                    "                    }\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";

            ObjectMapper objectMapper1 = new ObjectMapper();
            JsonNode jsonquery1 = objectMapper.readTree(query1);

            RestTemplate restTemplate1 = new RestTemplate();
            HttpEntity<JsonNode> request1 = new HttpEntity<>(jsonquery1);
            String fooResourceUrl1
                    = "http://localhost:9200/ncr/reviewer/_search?pretty";
            ResponseEntity<String> response1
                    = restTemplate1.postForEntity(fooResourceUrl1, request1, String.class);
            JsonNode rootNode1 = objectMapper1.readTree(response1.getBody());
            List<ChoseReviewersDTO> reviewers = this.getReviewersFromResponse(rootNode1);

            for(int rv=0;rv<retVal.size();rv++){
                for(ChoseReviewersDTO crdto:reviewers){
                    if(crdto.getId()==retVal.get(rv).getId() || crdto.getId().equals(retVal.get(rv).getId())){
                        retVal.remove(rv);
                    }
                }
            }
        }

        return new ResponseEntity(retVal, HttpStatus.OK);
    }

    @GetMapping(path = "/byTitle/{phrase}/{title}",
            produces = "application/json")
    @SuppressWarnings("Duplicates")
    public @ResponseBody
    ResponseEntity findByTitle(@PathVariable String title, @PathVariable Long phrase) throws Exception {
        String query = "";
        if (phrase == 0) {
            query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"match\" : {\n" +
                    "            \"title\" : {\n" +
                    "                \"query\" : \"" + title + "\",\n" +
                    "                \"analyzer\":\"serbian\"\n" +
                    "            }\n" +
                    "           \n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"highlight\" : {\n" +
                    "        \"fields\" : {\n" +
                    "            \"title\" : {\n" +
                    "            \t\"type\":\"plain\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
        } else if (phrase == 1) {
            query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"match_phrase\" : {\n" +
                    "            \"title\" : {\n" +
                    "                \"query\" : \"" + title + "\",\n" +
                    "                \"analyzer\":\"serbian\"\n" +
                    "         \n" +
                    "            }\n" +
                    "           \n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"highlight\" : {\n" +
                    "        \"fields\" : {\n" +
                    "            \"title\" : {\n" +
                    "            \t\"type\":\"plain\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
        }


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonquery = objectMapper.readTree(query);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<JsonNode> request = new HttpEntity<>(jsonquery);
        String fooResourceUrl
                = "http://localhost:9200/nc/work/_search?pretty";
        ResponseEntity<String> response
                = restTemplate.postForEntity(fooResourceUrl, request, String.class);
        JsonNode rootNode = objectMapper.readTree(response.getBody());
        JsonNode locatedNode = rootNode.path("hits").path("hits");
        List<WorkESdto> retVal = getRetVal(locatedNode, "title");


        return new ResponseEntity(retVal, HttpStatus.OK);
    }

    @GetMapping(path = "/byMagazineName/{phrase}/{magazineName}",
            produces = "application/json")
    @SuppressWarnings("Duplicates")
    public @ResponseBody
    ResponseEntity findBybyMagazineName(@PathVariable String magazineName, @PathVariable Long phrase) throws Exception {

        String query = "";
        if (phrase == 0) {
            query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"match\" : {\n" +
                    "            \"magazineName\" : {\n" +
                    "                \"query\" : \"" + magazineName + "\",\n" +
                    "                \"analyzer\":\"serbian\"\n" +
                    "            }\n" +
                    "           \n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"highlight\" : {\n" +
                    "        \"fields\" : {\n" +
                    "            \"magazineName\" : {\n" +
                    "            \t\"type\":\"plain\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
        } else if (phrase == 1) {
            query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"match_phrase\" : {\n" +
                    "            \"magazineName\" : {\n" +
                    "                \"query\" : \"" + magazineName + "\",\n" +
                    "                \"analyzer\":\"serbian\"\n" +
                    "         \n" +
                    "            }\n" +
                    "           \n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"highlight\" : {\n" +
                    "        \"fields\" : {\n" +
                    "            \"magazineName\" : {\n" +
                    "            \t\"type\":\"plain\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonquery = objectMapper.readTree(query);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<JsonNode> request = new HttpEntity<>(jsonquery);
        String fooResourceUrl
                = "http://localhost:9200/nc/work/_search?pretty";
        ResponseEntity<String> response
                = restTemplate.postForEntity(fooResourceUrl, request, String.class);
        JsonNode rootNode = objectMapper.readTree(response.getBody());
        JsonNode locatedNode = rootNode.path("hits").path("hits");
        List<WorkESdto> retVal = getRetVal(locatedNode, "magazineName");


        return new ResponseEntity(retVal, HttpStatus.OK);
    }

    @GetMapping(path = "/byKeyTerms/{phrase}/{keyTerms}",
            produces = "application/json")
    @SuppressWarnings("Duplicates")
    public @ResponseBody
    ResponseEntity findBybyKeyTerms(@PathVariable String keyTerms, @PathVariable Long phrase) throws Exception {

        String query = "";
        if (phrase == 0) {
            query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"match\" : {\n" +
                    "            \"keyTerms\" : {\n" +
                    "                \"query\" : \"" + keyTerms + "\",\n" +
                    "                \"analyzer\":\"serbian\"\n" +
                    "            }\n" +
                    "           \n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"highlight\" : {\n" +
                    "        \"fields\" : {\n" +
                    "            \"keyTerms\" : {\n" +
                    "            \t\"type\":\"plain\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
        } else if (phrase == 1) {
            query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"match_phrase\" : {\n" +
                    "            \"keyTerms\" : {\n" +
                    "                \"query\" : \"" + keyTerms + "\",\n" +
                    "                \"analyzer\":\"serbian\"\n" +
                    "         \n" +
                    "            }\n" +
                    "           \n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"highlight\" : {\n" +
                    "        \"fields\" : {\n" +
                    "            \"keyTerms\" : {\n" +
                    "            \t\"type\":\"plain\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
        }


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonquery = objectMapper.readTree(query);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<JsonNode> request = new HttpEntity<>(jsonquery);
        String fooResourceUrl
                = "http://localhost:9200/nc/work/_search?pretty";
        ResponseEntity<String> response
                = restTemplate.postForEntity(fooResourceUrl, request, String.class);
        JsonNode rootNode = objectMapper.readTree(response.getBody());
        JsonNode locatedNode = rootNode.path("hits").path("hits");
        List<WorkESdto> retVal = getRetVal(locatedNode, "keyTerms");


        return new ResponseEntity(retVal, HttpStatus.OK);
    }

    @GetMapping(path = "/byAuthors/{phrase}/{authors}",
            produces = "application/json")
    @SuppressWarnings("Duplicates")
    public @ResponseBody
    ResponseEntity findBybyAuthors(@PathVariable String authors, @PathVariable Long phrase) throws Exception {

        String query = "";
        if (phrase == 0) {
            query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"match\" : {\n" +
                    "            \"authors\" : {\n" +
                    "                \"query\" : \"" + authors + "\"\n" +
//                    "                \"analyzer\":\"serbian\"\n" +
                    "            }\n" +
                    "           \n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"highlight\" : {\n" +
                    "        \"fields\" : {\n" +
                    "            \"authors\" : {\n" +
                    "            \t\"type\":\"plain\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
        } else if (phrase == 1) {
            query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"match_phrase\" : {\n" +
                    "            \"authors\" : {\n" +
                    "                \"query\" : \"" + authors + "\"\n" +
//                    "                \"analyzer\":\"serbian\"\n" +
                    "         \n" +
                    "            }\n" +
                    "           \n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"highlight\" : {\n" +
                    "        \"fields\" : {\n" +
                    "            \"authors\" : {\n" +
                    "            \t\"type\":\"plain\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
        }


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonquery = objectMapper.readTree(query);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<JsonNode> request = new HttpEntity<>(jsonquery);
        String fooResourceUrl
                = "http://localhost:9200/nc/work/_search?pretty";
        ResponseEntity<String> response
                = restTemplate.postForEntity(fooResourceUrl, request, String.class);
        JsonNode rootNode = objectMapper.readTree(response.getBody());
        JsonNode locatedNode = rootNode.path("hits").path("hits");
        List<WorkESdto> retVal = getRetVal(locatedNode, "authors");


        return new ResponseEntity(retVal, HttpStatus.OK);
    }


    @GetMapping(path = "/byWorkContent/{phrase}/{workContent}",
            produces = "application/json")
    @SuppressWarnings("Duplicates")
    public @ResponseBody
    ResponseEntity findBybyWorkContent(@PathVariable String workContent, @PathVariable Long phrase) throws Exception {

        String query = "";
        if (phrase == 0) {
            query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"match\" : {\n" +
                    "            \"workContent\" : {\n" +
                    "                \"query\" : \"" + workContent + "\",\n" +
                    "                \"analyzer\":\"serbian\"\n" +
                    "            }\n" +
                    "           \n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"highlight\" : {\n" +
                    "        \"fields\" : {\n" +
                    "            \"workContent\" : {\n" +
                    "            \t\"type\":\"plain\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
        } else if (phrase == 1) {
            query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"match_phrase\" : {\n" +
                    "            \"workContent\" : {\n" +
                    "                \"query\" : \"" + workContent + "\",\n" +
                    "                \"analyzer\":\"serbian\"\n" +
                    "         \n" +
                    "            }\n" +
                    "           \n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"highlight\" : {\n" +
                    "        \"fields\" : {\n" +
                    "            \"workContent\" : {\n" +
                    "            \t\"type\":\"plain\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
        }


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonquery = objectMapper.readTree(query);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<JsonNode> request = new HttpEntity<>(jsonquery);
        String fooResourceUrl
                = "http://localhost:9200/nc/work/_search?pretty";
        ResponseEntity<String> response
                = restTemplate.postForEntity(fooResourceUrl, request, String.class);
        JsonNode rootNode = objectMapper.readTree(response.getBody());
        JsonNode locatedNode = rootNode.path("hits").path("hits");
        List<WorkESdto> retVal = getRetVal(locatedNode, "workContent");


        return new ResponseEntity(retVal, HttpStatus.OK);
    }



    @GetMapping(path="/byScienceAreas/{scienceAreas}",
    produces = "application/json")
    public @ResponseBody ResponseEntity findBySA(@PathVariable String scienceAreas){
        String[] areas=scienceAreas.split("-");
        List<Long> areasIds=new ArrayList<>();
        for(int i=0;i<areas.length;i++){
            areasIds.add(Long.parseLong(areas[i]));
        }
        List<WorkES> workES=new ArrayList<>();
        workES=workESRepository.findByScienceAreaIn(areasIds);

        List<WorkESdto> retVal=new ArrayList<>();
        for(WorkES wes:workES){
            WorkESdto dto=new WorkESdto();

            dto.setId(wes.getId());
            dto.setTitle(wes.getTitle());
            dto.setMagazineName(wes.getMagazineName());
            dto.setWorkAbstract(wes.getWorkAbstract());
            dto.setAuthors(wes.getAuthors());
            retVal.add(dto);
        }

        return new ResponseEntity(retVal,HttpStatus.OK);
    }


    public List<WorkESdto> getRetVal(JsonNode node,String highlight){
        List<WorkESdto> retVal=new ArrayList<>();
        for(int i=0;i<node.size();i++){
            WorkESdto dto=new WorkESdto();
            Long workId=Long.parseLong(node.get(i).path("_source").path("id").asText());
//            Work work=workRepository.findOneById(workId);
            dto.setAuthors(node.get(i).path("_source").path("authors").asText());
            dto.setId(workId);
            dto.setMagazineName(node.get(i).path("_source").path("magazineName").asText());
//            dto.setOpenAcess(work.getMagazine().isOpenAccess());
            dto.setTitle(node.get(i).path("_source").path("title").asText());
            dto.setWorkAbstract(node.get(i).path("_source").path("workAbstract").asText());
            System.out.println(node.get(i).path("highlight").path(highlight).toString());
            String highText="";
            for(int j=0;j<node.get(i).path("highlight").path(highlight).size();j++){
                highText+=node.get(i).path("highlight").path(highlight).get(j).asText()+"...";
            }
            dto.setHighlight(highText);
            retVal.add(dto);
        }
        return retVal;

    }

    public List<ChoseReviewersDTO> getReviewersFromResponse(JsonNode rootNode){

        List<ChoseReviewersDTO> reviewers=new ArrayList<>();
        JsonNode locatedNode = rootNode.path("hits").path("hits");

        for(int i=0;i<locatedNode.size();i++){
            ChoseReviewersDTO choseReviewersDTO=new ChoseReviewersDTO();
            Long reviewerId=Long.parseLong(locatedNode.get(i).path("_source").path("reviewerId").asText());
            choseReviewersDTO.setId(reviewerId);
            choseReviewersDTO.setName(locatedNode.get(i).path("_source").path("name").asText());
            reviewers.add(choseReviewersDTO);
        }

        return reviewers;
    }
}
