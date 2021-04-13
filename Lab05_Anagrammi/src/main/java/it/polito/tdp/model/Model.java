package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.anagrammi.db.AnagrammaDAO;

public class Model {
	AnagrammaDAO anagrammaDao=new AnagrammaDAO();
	
	public List <String> anagrammiCorretti(String parola){
		List<String> risultatoCorretti=new ArrayList<>();
		List<String> risultatoErrati=new ArrayList<>();
		permuta("",parola,0,risultatoCorretti,risultatoErrati);
		//cancella dalla lista le parole non valide (con un dizionario)
		return risultatoCorretti;
		
	}
	public List <String> anagrammiErrati(String parola){
		List<String> risultatoErrati=new ArrayList<>();
		List<String> risultatoCorretti=new ArrayList<>();
		permuta("",parola,0,risultatoCorretti,risultatoErrati);
		//cancella dalla lista le parole non valide (con un dizionario)
		return risultatoErrati;
		
	}
	
	private void permuta(String parziale,String lettere, int livello,List<String>risultatoCorretti,List<String>risultatoErrati)
	{
		//vedo se sono nel caso terminale, sennò faccio la ricorsione
		if(lettere.length()==0)
		{
			//la soluzione parziale è anche completa perchè ha una
			//stringa vuota 
			if(this.anagrammaDao.isCorrect(parziale))
			{
			risultatoCorretti.add(parziale);
			}
			else
				risultatoErrati.add(parziale);
		}
		else
		{
			//quando non sono nel caso terminale faccio una ricorsione
			// e ogni sottoproblema corrisponde a un nuovo carattere
			//da sistemare nelle parole
			for(int pos=0;pos<lettere.length();pos++)
			{
				//il tentativo è la string lettere alla posizione pos
				Character tentativo=lettere.charAt(pos);
				String nuovaParziale=parziale+tentativo;
				//lettere è lettere meno togli il carattere pos;
				String nuovaLettere=lettere.substring(0, pos)+lettere.substring(pos+1);
				
				//posso controllare se nuovaParziale è un prefisso che ha
				//senso nell'alfabeto italiano
				//"aqz"-->NO "car"-->SI
				permuta(nuovaParziale,nuovaLettere,livello+1,risultatoCorretti,risultatoErrati);
				
				//BackTracking (serve se ho "sporcato" le variabili e devo
				//sistemarle per la iterazione dopo)
				//in questo caso NON LA USIAMO perchè creiamo nuove variabili
				//rimetti a posto parziale 
				//rimetti a posto lettere
			}
		}
		
	}

}
