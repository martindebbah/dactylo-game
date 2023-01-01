package com.cpo.dactylogame.model.text;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Random;

public class RandomWords extends WordIterator {

    private String[] words = {"habenas", "interrogatus", "notas", "remittas", "ambigere", "convallibusque", "novo", "ego", "experiendum", "sese",
    "saevitiam", "patiuntur", "cornuta", "viri", "observabant", "noctes", "vis", "ab", "ac", "ad",
    "revertuntur", "prima", "firmi", "excellens", "lenire", "amicorum", "cecidisse", "senior", "curant", "meruerim",
    "viles", "commendari", "libentius", "hoc", "parcendo", "memorat", "repugnantibus", "fugiendas", "loqui", "repentina",
    "benevolentiam", "facinora", "frui", "caput", "quidem", "velut", "maxime", "sed", "tollitque", "ausurus",
    "maxima", "expeditionis", "saltu", "a", "fastus", "existimabat", "cedentium", "c", "indigens", "equo",
    "magna", "indigno", "diligendi", "iudicare", "perpetrataharum", "q", "socerum", "illae", "exigua", "quibusdam",
    "adferunt", "dicere", "senem", "de", "consiliis", "utilitates", "nimis", "sermo", "haberet", "ambigeret",
    "locutus", "senex", "clangore", "diu", "parandis", "post", "nimia", "amicitiae", "fiunt", "ita",
    "uxorem", "hibernas", "rem", "hercule", "res", "montibus", "valuisset", "veniunt", "amicitia", "eo",
    "et", "fannius", "sane", "posita", "ex", "loquetur", "habitus", "graecia", "recteque", "febrium",
    "mei", "signa", "quadam", "videbaturut", "haecque", "obtruncatis", "occultantes", "caesar", "metuens", "sic",
    "iudicarent", "asperitate", "luna", "effusos", "habere", "mirabilia", "primo", "abstergendae", "accitus", "sit",
    "amicitias", "oves", "virtutis", "amicum", "cogitatione", "opinor", "constantes", "meis", "pulchrum", "adducas",
    "homines", "gloriae", "sensim", "partim", "delatum", "tamquam", "rerum", "inbracteari", "languentibus", "vetustatis",
    "eos", "cessante", "pluribus", "repudiandae", "ultra", "molestum", "satis", "loca", "rebus", "saevientis",
    "id", "vero", "per", "amicos", "loco", "numquam", "expetendis", "in", "superque", "inter",
    "vivendum", "omnia", "sibi", "vera", "quadrupedo", "placuisse", "bithyniam", "suarum", "implicari", "omnis",
    "commorati", "moliretur", "precari", "sensu", "periculorum", "valet", "hostilia", "animis", "incendente", "honeste",
    "hortabatur", "gloria", "feci", "stirpis", "silvestribus", "ait", "montuosis", "constantius", "consecutae", "prorsus",
    "magnae", "praecurrit", "dira", "auro", "nisi", "maritus", "quas", "quam", "parumper", "capras",
    "temporibus", "moribus", "regiae", "fulgente", "diligentiores", "censorius", "fregerat", "communibus", "adepturi", "quae",
    "esse", "inquit", "ipsa", "nulli", "tubarum", "est", "instar", "ipse", "passibus", "ipsi",
    "nullo", "nec", "sapiens", "potestatemrestabat", "erat", "nunc", "me", "desideratam", "putes", "modo",
    "repentes", "libro", "terminos", "solido", "verae", "spe", "nemo", "ancoralia", "spernentem", "plus",
    "fallacibus", "appareat", "supplicare", "adhibere", "neglegentis", "ne", "tempestates", "germanum", "contemplans", "eum",
    "aviditate", "cuncta", "primitiis", "confidit", "causa", "blanditiis", "fruantursed", "inanima", "anxia", "locato",
    "nihil", "provectus", "illi", "ob", "earum", "acerbius", "africani", "quidam", "amicitia", "consuevit",
    "superasset", "amicitiis", "illa", "opinione", "fortasse", "easque", "ille", "aliquem", "ipso", "multae",
    "posse", "quem", "quamquam", "opinantibus", "nulla", "disputatio", "laelius", "petivere", "gradu", "quid",
    "nondum", "scripsi", "laelium", "tum", "conservanda", "galli", "plurimum", "legens", "inpetraverim", "persequantur",
    "arduos", "adsistebant", "tota", "saepe", "munitus", "essent", "malo", "expertum", "profecta", "africanus",
    "senectute", "monitae", "praemii", "igitur", "suae", "constantini", "impeditis", "suam", "ipsum", "principale",
    "animal", "cum", "emensos", "suspensis", "ascraeus", "cur", "procurrens", "amicis", "regem", "delatae",
    "suo", "consuetudinis", "locis", "se", "colendisque", "penuria", "obitum", "si", "seseque", "potestatis",
    "appeterent", "audio", "scaphas", "factorum", "consuetudo", "turbinis", "statuam", "ulli", "argutiis", "sapientia",
    "eius", "splendore", "detrahique", "te", "opimas", "delectemur", "aliquando", "vicissim", "quin", "statuas",
    "iudicium", "amicissimus", "tu", "etiam", "laborum", "gentilitateque", "oritur", "caesaris", "redeo", "saepe",
    "virtute", "cruentum", "avertas", "ut", "nimias", "nullis", "pro", "armisque", "foedabat", "vi",
    "difficile", "cuique", "cuius", "idonei", "qua", "monstravit", "dilexit", "unus", "qui", "querebatur",
    "unum", "egeat", "quo", "propinquitate", "sapientes", "quod", "non", "fiduciam", "culmen", "insectarique",
    "arduis", "commodis", "tamen", "somnum", "poterit", "causae", "generis", "gravius", "detrahunt", "autem",
    "boni", "herbis", "quibus", "insperato", "antiochum", "laxissimas", "prudentior", "efferebatur", "excellit", "quisque",
    "tendere", "mari", "multis", "suaque", "fictisque", "haerebatnam", "beate", "vetustas", "bonos", "habitos",
    "mussitaresed", "multa", "figmentis", "eisdem", "sumusatque", "stationes", "faceremus", "utatur", "nostris", "cognoscespost",
    "trium", "pastae", "assentior", "fere", "potius", "ardenter", "statione", "nauticos", "aeternitati", "velis",
    "modum", "viis", "iniectantes", "facimus", "gallicanos", "impediat", "velim", "respondet", "sororem", "avertebant",
    "properaret", "carentibus", "animatus", "sed", "suis", "vates", "ferae", "habebat", "quot", "quos",
    "amicorum", "venire", "ascensus", "absumpta", "confinia", "ideoque", "multaeque", "aetatis", "eventus", "alienis",
    "procellae", "iis", "partium", "nominis", "admiratione", "adfectant", "eas", "mortem", "pluribusnovitates", "sollicitum",
    "auxit", "fortunae", "haec", "illis", "amici", "experiendi", "aestimantes", "introisset", "felicitatis", "cato",
    "tullius", "possit", "appellatur", "ubi", "miseriarum", "acilio", "quaedam", "aereis", "enim", "vehementius",
    "eligendis", "his", "honestissime", "licet", "nostra", "squalore", "fructus", "conscientia", "vel", "sunt",
    "diutius", "illius", "enim", "essent", "intractato", "animus", "ipsis", "animum", "securitatem", "esse",
    "milite", "mucius", "degressi", "sic", "multos", "tandem", "glabrioni", "sententiarum", "quasi", "fame",
    "scipionem", "curam", "stabiles", "varietas", "iudicet", "mentionem", "imo", "minima", "suspicionis", "plerumque",
    "omnibus", "minime", "alias", "eligendi", "adultae", "caenos", "fultum", "infudere", "auctorem", "necesse",
    "invehi", "parturiat", "insuperabilis", "commodissimum", "spem", "merces", "latebrosis", "longos", "sentirent", "locum",};
    private Random random = new Random();

    /**
     * Créer un texte aléatoire à partir d'un fichier texte, si "" en argument, les mots sont choisis parmis
     * le tableau initial
     * @param text Le fichier texte existant
     */
    public RandomWords(String text) {
        if (text.equals(""))
            return;

        words = new String[0];
        if (text.equals("citation")) { // Si le joueur choisi les citations, on les ajoute toutes
            for (int i = 1; i < NCIT + 1; i++)
                createArray(text + i);
        }else {
            createArray(text);
        }
    }

    /**
     * Crée le tableau contenant les mots
     * @param text Le fichier texte
     */
    private void createArray(String text) {
        try{
            HashSet<String> s = new HashSet<>();
            Reader r = new Reader(text);
            while (r.hasNext()) {
                s.add(r.next());
            }
            for (int i = 0; i < words.length; i++)
                s.add(words[i]);
            words = s.toArray(words);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public String next() {
        return words[random.nextInt(words.length)];
    }
    
}
