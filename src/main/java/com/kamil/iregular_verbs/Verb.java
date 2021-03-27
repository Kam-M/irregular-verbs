package com.kamil.iregular_verbs;

public class Verb implements Comparable<Verb> {
	
	private String infinitive;
	private String pastTense;
	private String pastParticiple;
	private String translation;
	private boolean isLearnt;

	public Verb(String infinitive, String pastTense, String pastParticiple, String translation, boolean isLearnt) {
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
	
	public boolean isLearnt() {
		return isLearnt;
	}

	public void setLearnt(boolean isLearnt) {
		this.isLearnt = isLearnt;
	}
	
	@Override
	public String toString() {
		return this.infinitive + " -- " + pastTense + " -- " + pastParticiple + " || " + translation;
	}

	public String toStringByTranslation() {
		return this.translation + " || " + infinitive + " -- " + pastTense + " -- " + pastParticiple;

	}

	@Override
	public int compareTo(Verb verbToCompare) {
			return this.infinitive.compareTo(verbToCompare.getInfinitive());
		}
	}
