package com.kamil.iregular_verbs;

public class Verb implements Comparable<Verb> {
	
	private String infinitive;
	private String pastTense;
	private String pastParticiple;
	private String translation;
	private int isLearnt;

	public Verb(String infinitive, String pastTense, String pastParticiple, String translation, int isLearnt) {
		this.infinitive = infinitive;
		this.pastTense = pastTense;
		this.pastParticiple = pastParticiple;
		this.translation = translation;
		this.isLearnt = isLearnt;
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
