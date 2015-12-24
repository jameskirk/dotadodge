package com.fourthskyinteractive.dx4j.d3d9.core;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.free.fr/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("d3d9") 
public class D3DVIEWPORT9 extends StructObject {
	public D3DVIEWPORT9() {
		super();
	}
	public D3DVIEWPORT9(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
	@Field(0) 
	public int X() {
		return this.io.getIntField(this, 0);
	}
	@Field(0) 
	public D3DVIEWPORT9 X(int X) {
		this.io.setIntField(this, 0, X);
		return this;
	}
	@Field(1) 
	public int Y() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public D3DVIEWPORT9 Y(int Y) {
		this.io.setIntField(this, 1, Y);
		return this;
	}
	@Field(2) 
	public int Width() {
		return this.io.getIntField(this, 2);
	}
	@Field(2) 
	public D3DVIEWPORT9 Width(int Width) {
		this.io.setIntField(this, 2, Width);
		return this;
	}
	@Field(3) 
	public int Height() {
		return this.io.getIntField(this, 3);
	}
	@Field(3) 
	public D3DVIEWPORT9 Height(int Height) {
		this.io.setIntField(this, 3, Height);
		return this;
	}
	@Field(4) 
	public float MinZ() {
		return this.io.getFloatField(this, 4);
	}
	@Field(4) 
	public D3DVIEWPORT9 MinZ(float MinZ) {
		this.io.setFloatField(this, 4, MinZ);
		return this;
	}
	@Field(5) 
	public float MaxZ() {
		return this.io.getFloatField(this, 5);
	}
	@Field(5) 
	public D3DVIEWPORT9 MaxZ(float MaxZ) {
		this.io.setFloatField(this, 5, MaxZ);
		return this;
	}
}
