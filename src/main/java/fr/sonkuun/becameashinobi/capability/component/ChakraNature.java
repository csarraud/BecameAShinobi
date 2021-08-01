package fr.sonkuun.becameashinobi.capability.component;

import fr.sonkuun.becameashinobi.capability.component.nature.DotonNature;
import fr.sonkuun.becameashinobi.capability.component.nature.FutonNature;
import fr.sonkuun.becameashinobi.capability.component.nature.KatonNature;
import fr.sonkuun.becameashinobi.capability.component.nature.RaitonNature;
import fr.sonkuun.becameashinobi.capability.component.nature.SuitonNature;

public class ChakraNature {

	private KatonNature katon;
	private SuitonNature suiton;
	private DotonNature doton;
	private RaitonNature raiton;
	private FutonNature futon;
	
	public ChakraNature() {
		
		this.katon = new KatonNature();
		this.suiton = new SuitonNature();
		this.doton = new DotonNature();
		this.raiton = new RaitonNature();
		this.futon = new FutonNature();
	}
	
	public ChakraNature(ChakraNature chakraNature) {
		
		this.katon = new KatonNature(chakraNature.getKaton());
		this.suiton = new SuitonNature(chakraNature.getSuiton());
		this.doton = new DotonNature(chakraNature.getDoton());
		this.raiton = new RaitonNature(chakraNature.getRaiton());
		this.futon = new FutonNature(chakraNature.getFuton());
	}

	public KatonNature getKaton() {
		return katon;
	}

	public void setKaton(KatonNature katon) {
		this.katon = new KatonNature(katon);
	}

	public SuitonNature getSuiton() {
		return suiton;
	}

	public void setSuiton(SuitonNature suiton) {
		this.suiton = new SuitonNature(suiton);
	}

	public DotonNature getDoton() {
		return doton;
	}

	public void setDoton(DotonNature doton) {
		this.doton = new DotonNature(doton);
	}

	public RaitonNature getRaiton() {
		return raiton;
	}

	public void setRaiton(RaitonNature raiton) {
		this.raiton = new RaitonNature(raiton);
	}

	public FutonNature getFuton() {
		return futon;
	}

	public void setFuton(FutonNature futon) {
		this.futon = new FutonNature(futon);
	}
}
