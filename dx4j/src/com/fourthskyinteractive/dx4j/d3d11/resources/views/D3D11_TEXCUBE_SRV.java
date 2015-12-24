package com.fourthskyinteractive.dx4j.d3d11.resources.views;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.free.fr/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("d3d11") 
public class D3D11_TEXCUBE_SRV extends StructObject {
	public D3D11_TEXCUBE_SRV() {
		super();
	}
	public D3D11_TEXCUBE_SRV(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
	@Field(0) 
	public int MostDetailedMip() {
		return this.io.getIntField(this, 0);
	}
	@Field(0) 
	public D3D11_TEXCUBE_SRV MostDetailedMip(int MostDetailedMip) {
		this.io.setIntField(this, 0, MostDetailedMip);
		return this;
	}
	@Field(1) 
	public int MipLevels() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public D3D11_TEXCUBE_SRV MipLevels(int MipLevels) {
		this.io.setIntField(this, 1, MipLevels);
		return this;
	}
}
