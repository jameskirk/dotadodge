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
public class D3D11_BUFFER_RTV extends StructObject {
	public D3D11_BUFFER_RTV() {
		super();
	}
	public D3D11_BUFFER_RTV(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
	
	@Field(0) 
	public int FirstElement() {
		return this.io.getIntField(this, 0);
	}
	@Field(0) 
	public D3D11_BUFFER_RTV FirstElement(int FirstElement) {
		this.io.setIntField(this, 0, FirstElement);
		return this;
	}
	@Field(value = 1, unionWith = 0)
	public int ElementOffset() {
		return this.io.getIntField(this, 0);
	}
	@Field(value = 1, unionWith = 0)
	public D3D11_BUFFER_RTV ElementOffset(int ElementOffset) {
		this.io.setIntField(this, 0, ElementOffset);
		return this;
	}
	@Field(2) 
	public int NumElements() {
		return this.io.getIntField(this, 1);
	}
	@Field(2) 
	public D3D11_BUFFER_RTV NumElements(int NumElements) {
		this.io.setIntField(this, 1, NumElements);
		return this;
	}
	@Field(value = 3, unionWith = 2) 
	public int ElementWidth() {
		return this.io.getIntField(this, 1);
	}
	@Field(value = 3, unionWith = 2)
	public D3D11_BUFFER_RTV ElementWidth(int ElementWidth) {
		this.io.setIntField(this, 1, ElementWidth);
		return this;
	}
}
