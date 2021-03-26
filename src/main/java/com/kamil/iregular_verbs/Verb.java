package com.kamil.iregular_verbs;

public class Verb implements Comparable<Verb> {
	
	private String translation;
	private String infinitive;
	private String pastTense;
	private String pastParticiple;

	public Verb(String translation, String infinitive, String pastTense, String pastParticiple) {
		this.infinitive = infinitive;
		this.translation = translation;
		this.pastTense = pastTense;
		this.pastParticiple = pastParticiple;
	}

	public String getTranslation() {
		return translation;
	}

	public String getInfinitive() {
		return infinitive;
	}

	public String getPastTense() {
		return pastTense;
	}

	public String getPastParticiple() {
		return pastParticiple;
	}
	
	@Override
	public String toString() {
		return infinitive + " -- " + pastTense + " -- " + pastParticiple + " || " + translation;
	}

	public String toStringByTranslation() {
		return translation + " || " + infinitive + " -- " + pastTense + " -- " + pastParticiple;

	}

	@Override
	public int compareTo(Verb verbToCompare) {
			return this.infinitive.compareTo(verbToCompare.getInfinitive());
		}
	}
