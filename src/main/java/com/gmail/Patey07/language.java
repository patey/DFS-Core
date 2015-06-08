package com.gmail.Patey07;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;

public class language {
	public Logger getLogger() {
	    return DFSCore.getLogger();
	}
	
	public String translate (String message,String race){
		String[] words = split(message);
		String[] cleaned = removeCommas(words);
		String[] cleaned2 = removePeriods(cleaned);
		String[] cleaned3 = removeSpecial(cleaned2);
		String[] functions = findFunctions(cleaned3,race);
		String[] doubles = removeDouble(functions);
		String[] combos = matchComb(doubles);
		String[] sounds = matchSounds(combos);
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i< sounds.length; i++) {
		    builder.append(sounds[i]);
		    if (i != sounds.length - 1){
		    	builder.append(" ");
		    }
		}
		getLogger().info(builder.toString());
		return builder.toString();
	}
	
	public String[] removeCommas(String[] words){
		Pattern pattern = Pattern.compile("(?<=[a-zA-Z0-9])[,](?=[a-zA-Z0-9])");
		List<String> newWords = new ArrayList<String>();
		for (int i = 0; i < words.length; i++){
			Matcher matcher = pattern.matcher(words[i]);
			if (matcher.find()){
				words[i] = matcher.replaceAll(" ");
				String[] splitWords = split(words[i]);
				for (int j = 0; j < splitWords.length; j++){
					newWords.add(splitWords[j]);
				}
			}else{
				newWords.add(words[i]);
			}
		}
		String[] wordsc = new String[newWords.size()];
		wordsc = newWords.toArray(wordsc);
		return wordsc;
	}
	
	public String[] removePeriods(String[] words){
		Pattern pattern = Pattern.compile("(?<=[a-zA-Z0-9])[.](?=[a-zA-Z0-9])");
		List<String> newWords = new ArrayList<String>();
		for (int i = 0; i < words.length; i++){
			Matcher matcher = pattern.matcher(words[i]);
			if (matcher.find()){
				words[i] = matcher.replaceAll(" ");
				String[] splitWords = split(words[i]);
				for (int j = 0; j < splitWords.length; j++){
					newWords.add(splitWords[j]);
				}
			}else{
				newWords.add(words[i]);
			}
		}
		String[] wordsc = new String[newWords.size()];
		wordsc = newWords.toArray(wordsc);
		return wordsc;
	}
	
	public String[] removeSpecial(String[] words){
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
		for (int i = 0; i < words.length; i++){
			Matcher matcher = pattern.matcher(words[i]);
			if (matcher.find()){
				words[i] = matcher.replaceAll("");
			}
		}
		return words;
	}
	
	public String[] split(String message){
		String[] words = message.split("\\s+");
		return words;
	}
	
	public String[] findFunctions(String[] words,String race){
		if (race.equals("spirit")){
			return words;
		}
		Pattern patterni = Pattern.compile("[iI]");
		Pattern patternme = Pattern.compile("[mM][eE]");
		Pattern patternwe = Pattern.compile("[wW][eE]");
		Pattern patternus = Pattern.compile("[uU][sS]");
		Pattern patternyou = Pattern.compile("[yY][oO][uU]");
		Pattern patternit = Pattern.compile("[iI][tT]");
		Pattern patternhe = Pattern.compile("[hH][eE]");
		Pattern patternhim = Pattern.compile("[hH][iI][mM]");
		Pattern patternshe = Pattern.compile("[sS][hH][eE]");
		Pattern patternher = Pattern.compile("[hH][eE][rR]");
		Pattern patternthey = Pattern.compile("[tT][hH][eE][yY]");
		Pattern patternthem = Pattern.compile("[tT][hH][eE][mM]");
		Pattern patterna = Pattern.compile("[aA]");
		Pattern patternthe = Pattern.compile("[tT][hH][eE]");
		Pattern patternthis = Pattern.compile("[tT][hH][iI][sS]");
		Pattern patternthat = Pattern.compile("[tT][hH][aA][tT]");
		Pattern patternwas = Pattern.compile("[wW][aA][sS]");
		Pattern patternis = Pattern.compile("[iI][sS]");
		Pattern patternbe = Pattern.compile("[bB][eE]");
		Pattern patternwill = Pattern.compile("[wW][iI][lL][lL]");
		Pattern patternmy = Pattern.compile("[mM][yY]");
		Pattern patternposess = Pattern.compile("(?<=['])[sS]");
		Pattern patternour = Pattern.compile("[oO][uU][rR]");
		Pattern patternhers = Pattern.compile("[hH][eE][rR][sS]");
		Pattern patternhis = Pattern.compile("[hH][iI][sS]");
		Pattern patterntheir = Pattern.compile("[tT][hH][eE][rR]");
		Pattern patternits = Pattern.compile("[iI][tT][sS]");
		Pattern patternas = Pattern.compile("[aA][sS]");
		Pattern patternin = Pattern.compile("[iI][nN]");
		Pattern patternto = Pattern.compile("[tT][oO]");
		Pattern patternon = Pattern.compile("[oO][nN]");
		Pattern patternand = Pattern.compile("[aA][nN][dD]");
		Pattern patternif = Pattern.compile("[iI][fF]");
		Pattern patternor = Pattern.compile("[oO][rR]");
		Pattern patternbut = Pattern.compile("[bB][uU][tT]");
		Pattern patternfrom = Pattern.compile("[fF][rR][oO][mM]");
		Pattern patternfor = Pattern.compile("[fF][oO][rR]");
		Pattern patternitss = Pattern.compile("[iI][tT]['][sS]");
		Pattern patternyes = Pattern.compile("[yY][eE][sS]");
		Pattern patternno = Pattern.compile("[nN][oO]");
		Pattern patternnot = Pattern.compile("[nN][oO][tT]");
		
		String[] etranslations = {"ile","ile","ilnu","tan","til","twe","twe","nye","nye","lenu","ei","lu","lute","luna","den","dua","nawe","ilne","ne","ilune","nyene","twene","lenune","tilne","yen","tul","nao","ina","an","la","lin","to","fai","tal","til dua","thal","ain"};
		String[] dtranslations = {"kah","kah","choth","kech","zet","nem","nem","nek","nek","sha","tash","dash","chat","kat","hash","chit","chit","ksak","yash","kat","yash","nek","nem","sha","zet","bath","kash","gut","kudch","shut","kan","thet","kug","sech","gush","zet chit","kud","dich"};
		String[] htranslations = {"ai","te","gen","yas","et","ha","hi","sa","si","tes","ta","da","tan","dan","vas","es","es","yet","mit","ez","ir","sal","har","tens","vis","zat","nam","du","en","ent","ev","al","bod","fem","fal","et es","yan","tor"};
		String[] ktranslations = {"pa","pa","pa","dod","dod","dod","dod","dod","dod","dod","da","da","da","da","ga","go","go","go","pa","dod","pa","dod","dod","dod","gog","gap","dod","po","da","dopa","gag","god","gapo","dop","dap","dod go","doda","gaga"};
		String[] translations = new String[38];
		if (race.equals("dwarf")){
			translations = dtranslations;
		}
		if (race.equals("elf")){
			translations = etranslations;
		}
		if (race.equals("human")){
			translations = htranslations;
		}
		if (race.equals("kobold")){
			translations = ktranslations;
		}
		
		for (int i = 0; i < words.length; i++){
			Matcher matcheri = patterni.matcher(words[i]);
			Matcher matcherme = patternme.matcher(words[i]);
			Matcher matcherwe = patternwe.matcher(words[i]);
			Matcher matcherus = patternus.matcher(words[i]);
			Matcher matcheryou = patternyou.matcher(words[i]);
			Matcher matcherit = patternit.matcher(words[i]);
			Matcher matcherhe = patternhe.matcher(words[i]);
			Matcher matcherhim = patternhim.matcher(words[i]);
			Matcher matchershe = patternshe.matcher(words[i]);
			Matcher matcherher = patternher.matcher(words[i]);
			Matcher matcherthey = patternthey.matcher(words[i]);
			Matcher matcherthem = patternthem.matcher(words[i]);
			Matcher matchera = patterna.matcher(words[i]);
			Matcher matcherthe = patternthe.matcher(words[i]);
			Matcher matcherthis = patternthis.matcher(words[i]);
			Matcher matcherthat = patternthat.matcher(words[i]);
			Matcher matcherwas = patternwas.matcher(words[i]);
			Matcher matcheris = patternis.matcher(words[i]);
			Matcher matcherbe = patternbe.matcher(words[i]);
			Matcher matcherwill = patternwill.matcher(words[i]);
			Matcher matchermy = patternmy.matcher(words[i]);
			Matcher matcherposess = patternposess.matcher(words[i]);
			Matcher matcherour= patternour.matcher(words[i]);
			Matcher matcherhers = patternhers.matcher(words[i]);
			Matcher matcherhis = patternhis.matcher(words[i]);
			Matcher matchertheir = patterntheir.matcher(words[i]);
			Matcher matcherits = patternits.matcher(words[i]);
			Matcher matcheras = patternas.matcher(words[i]);
			Matcher matcherin = patternin.matcher(words[i]);
			Matcher matcherto = patternto.matcher(words[i]);
			Matcher matcheron = patternon.matcher(words[i]);
			Matcher matcherand = patternand.matcher(words[i]);
			Matcher matcherif = patternif.matcher(words[i]);
			Matcher matcheror = patternor.matcher(words[i]);
			Matcher matcherbut = patternbut.matcher(words[i]);
			Matcher matcherfrom = patternfrom.matcher(words[i]);
			Matcher matcherfor = patternfor.matcher(words[i]);
			Matcher matcheritss = patternitss.matcher(words[i]);
			Matcher matcheryes = patternyes.matcher(words[i]);
			Matcher matcherno = patternno.matcher(words[i]);
			Matcher matchernot = patternnot.matcher(words[i]);
			
			if (matcherposess.find()){
				words[i] = matcherposess.replaceAll(translations[20]);
			}
			
			if (words[i].length() == 1){
				if (matcheri.find()){
					words[i] = matcheri.replaceAll(translations[0]);
				}
				if (matchera.find()){
					words[i] = matchera.replaceAll(translations[10]);
				}
			}else{
				words[i] = words[i];
			}
			if(words[i].length() == 2){
				if (matcherme.find()){
					words[i] = matcherme.replaceAll(translations[1]);
				}
				if (matcherwe.find()){
					words[i] = matcherwe.replaceAll(translations[2]);
				}
				if (matcherus.find()){
					words[i] = matcherus.replaceAll(translations[2]);
				}
				if (matcherit.find()){
					words[i] = matcherit.replaceAll(translations[4]);
				}
				if (matcherhe.find()){
					words[i] = matcherhe.replaceAll(translations[5]);
				}
				if (matcheris.find()){
					words[i] = matcheris.replaceAll(translations[15]);
				}
				if (matcherbe.find()){
					words[i] = matcherbe.replaceAll(translations[16]);
				}
				if (matchermy.find()){
					words[i] = matchermy.replaceAll(translations[18]);
				}
				if (matcheras.find()){
					words[i] = matcheras.replaceAll(translations[25]);
				}
				if (matcherin.find()){
					words[i] = matcherin.replaceAll(translations[26]);
				}
				if (matcherto.find()){
					words[i] = matcherto.replaceAll(translations[27]);
				}
				if (matcheron.find()){
					words[i] = matcheron.replaceAll(translations[28]);
				}
				if (matcherif.find()){
					words[i] = matcherif.replaceAll(translations[30]);
				}
				if (matcheror.find()){
					words[i] = matcheror.replaceAll(translations[31]);
				}
				if (matcherno.find()){
					words[i] = matcherno.replaceAll(translations[37]);
				}
			}
			if (words[i].length() == 3){
				if (matcheryou.find()){
					words[i] = matcheryou.replaceAll(translations[3]);
				}
				if (matcherhim.find()){
					words[i] = matcherhim.replaceAll(translations[6]);
				}
				if (matchershe.find()){
					words[i] = matchershe.replaceAll(translations[7]);
				}
				if (matcherher.find()){
					words[i] = matcherher.replaceAll(translations[8]);
				}
				if (matcherthe.find()){
					words[i] = matcherthe.replaceAll(translations[11]);
				}
				if (matcherwas.find()){
					words[i] = matcherwas.replaceAll(translations[14]);
				}
				if (matcherour.find()){
					words[i] = matcherour.replaceAll(translations[20]);
				}
				if (matcherhis.find()){
					words[i] = matcherhis.replaceAll(translations[22]);
				}
				if (matcherits.find()){
					words[i] = matcherits.replaceAll(translations[24]);
				}
				if (matcherand.find()){
					words[i] = matcherand.replaceAll(translations[29]);
				}
				if (matcherbut.find()){
					words[i] = matcherbut.replaceAll(translations[32]);
				}
				if (matcherfor.find()){
					words[i] = matcherfor.replaceAll(translations[34]);
				}
				if (matcheryes.find()){
					words[i] = matcheryes.replaceAll(translations[36]);
				}
				if (matchernot.find()){
					words[i] = matchernot.replaceAll(translations[37]);
				}
			}
			
			if (words[i].length() == 4){
				if (matcherthey.find()){
					words[i] = matcherthey.replaceAll(translations[9]);
				}
				if (matcherthem.find()){
					words[i] = matcherthem.replaceAll(translations[9]);
				}
				if (matcherthis.find()){
					words[i] = matcherthis.replaceAll(translations[12]);
				}
				if (matcherthat.find()){
					words[i] = matcherthat.replaceAll(translations[13]);
				}
				if (matcherwill.find()){
					words[i] = matcherwill.replaceAll(translations[17]);
				}
				if (matcherhers.find()){
					words[i] = matcherhers.replaceAll(translations[21]);
				}
				if (matcherfrom.find()){
					words[i] = matcherfrom.replaceAll(translations[33]);
				}
			}
			
			if (words[i].length() == 5){
				if (matchertheir.find()){
					words[i] = matchertheir.replaceAll(translations[23]);
				}
				if (matcheritss.find()){
					words[i] = matcheritss.replaceAll(translations[35]);
				}
			}
		}
		return words;
	}
	
	public String[] removeDouble(String[] words){
		Pattern pattern = Pattern.compile("(\\w)\\1+");
		for (int i = 0; i < words.length; i++){
			Matcher matcher = pattern.matcher(words[i]);
			if (matcher.find()){
				words[i] = matcher.replaceAll("$1");
			}
		}
		return words;
	}
	
	public String[] matchComb(String[] words){
		Pattern patternth = Pattern.compile("[tT][hH]");
		
		for (int i = 0; i < words.length; i++){
			Matcher matcherth = patternth.matcher(words[i]);
			
			while (matcherth.find()){
				words[i] = matcherth.replaceAll("[th]");
			}	
		}
		return matchComb2(words);
	}
	
	public String[] matchComb2(String[] words){
		Pattern patternch = Pattern.compile("[cC][hH]");
		
		for (int i = 0; i < words.length; i++){
			Matcher matcherch = patternch.matcher(words[i]);
			
			while (matcherch.find()){
				words[i] = matcherch.replaceAll("[ch]");
			}	
		}
		return matchComb3(words);
	}
	
	public String[] matchComb3(String[] words){
		Pattern patternsh = Pattern.compile("[sS][hH]");
		
		for (int i = 0; i < words.length; i++){
			Matcher matchersh = patternsh.matcher(words[i]);
			
			while (matchersh.find()){
				words[i] = matchersh.replaceAll("[sh]");
			}	
		}
		return matchComb4(words);
	}
	
	public String[] matchComb4(String[] words){
		Pattern patternng = Pattern.compile("[nN][gG]");
		
		for (int i = 0; i < words.length; i++){
			Matcher matcherng = patternng.matcher(words[i]);
			
			while (matcherng.find()){
				words[i] = matcherng.replaceAll("[ng]");
			}	
		}
		return words;
	}
	
	public String[] matchSounds(String[] words){
		
		Pattern patternx = Pattern.compile("[xX]");
		
		for (int i = 0; i < words.length; i++){
			
			Matcher matcherx = patternx.matcher(words[i]);
			
			while (matcherx.find()){
				words[i] = matcherx.replaceAll("[ks]");
			}	
		}
		return matchSounds2(words);
	}
	
	public String[] matchSounds2(String[] words){
		
		Pattern patternqu = Pattern.compile("[qQ][uU]");
		
		for (int i = 0; i < words.length; i++){
			Matcher matcherqu = patternqu.matcher(words[i]);
			
			while (matcherqu.find()){
				words[i] = matcherqu.replaceAll("[kw]");
			}
		}
		return matchSounds3(words);
	}
	
	public String[] matchSounds3(String[] words){
		
		Pattern patternq = Pattern.compile("[qQ]");
		
		for (int i = 0; i < words.length; i++){
			Matcher matcherq = patternq.matcher(words[i]);
			
			while (matcherq.find()){
				words[i] = matcherq.replaceAll("[kw]");
			}
		}
		return matchSounds4(words);
	}
	
	public String[] matchSounds4(String[] words){
		
		Pattern patternwh = Pattern.compile("[wW][hH]");
		
		for (int i = 0; i < words.length; i++){
			Matcher matcherwh = patternwh.matcher(words[i]);
			
			while (matcherwh.find()){
				words[i] = matcherwh.replaceAll("[w]");
			}
		}
		return matchSounds5(words);
	}
	
	public String[] matchSounds5(String[] words){
		
		Pattern patternc = Pattern.compile("^[cC](?![hH])");
		
		for (int i = 0; i < words.length; i++){
			Matcher matcherc = patternc.matcher(words[i]);
			
			while (matcherc.find()){
				words[i] = matcherc.replaceFirst("[k]");
			}
		}
		return matchSounds6(words);
	}
	
	public String[] matchSounds6(String[] words){
		
		Pattern patterntion = Pattern.compile("[tT][iI][oO][nN]");
		
		for (int i = 0; i < words.length; i++){
			Matcher matchertion = patterntion.matcher(words[i]);
			
			while (matchertion.find()){
				words[i] = matchertion.replaceAll("[shen]");
			}
		}
		return matchSounds7(words);
	}
	
	public String[] matchSounds7(String[] words){
		
		Pattern patternmb = Pattern.compile("[mM][bB]");
		
		for (int i = 0; i < words.length; i++){
			Matcher matchermb = patternmb.matcher(words[i]);
			
			while (matchermb.find()){
				words[i] = matchermb.replaceAll("[m]");
			}
		}
		return matchSounds8(words);
	}
	
	public String[] matchSounds8(String[] words){
		
		Pattern patternph = Pattern.compile("[pP][hH]");
		
		for (int i = 0; i < words.length; i++){
			Matcher matcherph = patternph.matcher(words[i]);
			
			while (matcherph.find()){
				words[i] = matcherph.replaceAll("[f]");
			}
		}
		return words;
	}
}
