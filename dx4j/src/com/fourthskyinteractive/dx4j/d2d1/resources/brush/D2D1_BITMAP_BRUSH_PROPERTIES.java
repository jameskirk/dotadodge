package com.fourthskyinteractive.dx4j.d2d1.resources.brush;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ValuedEnum;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_BITMAP_INTERPOLATION_MODE;
import com.fourthskyinteractive.dx4j.d2d1.D2D1.D2D1_EXTEND_MODE;
/**
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.free.fr/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("d2d1") 
public class D2D1_BITMAP_BRUSH_PROPERTIES extends StructObject {
	public D2D1_BITMAP_BRUSH_PROPERTIES() {
		super();
	}
	public D2D1_BITMAP_BRUSH_PROPERTIES(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
	/// C type : D2D1_EXTEND_MODE
	@Field(0) 
	public ValuedEnum<D2D1_EXTEND_MODE > extendModeX() {
		return this.io.getEnumField(this, 0);
	}
	/// C type : D2D1_EXTEND_MODE
	@Field(0) 
	public D2D1_BITMAP_BRUSH_PROPERTIES extendModeX(ValuedEnum<D2D1_EXTEND_MODE > extendModeX) {
		this.io.setEnumField(this, 0, extendModeX);
		return this;
	}
	/// C type : D2D1_EXTEND_MODE
	@Field(1) 
	public ValuedEnum<D2D1_EXTEND_MODE > extendModeY() {
		return this.io.getEnumField(this, 1);
	}
	/// C type : D2D1_EXTEND_MODE
	@Field(1) 
	public D2D1_BITMAP_BRUSH_PROPERTIES extendModeY(ValuedEnum<D2D1_EXTEND_MODE > extendModeY) {
		this.io.setEnumField(this, 1, extendModeY);
		return this;
	}
	/// C type : D2D1_BITMAP_INTERPOLATION_MODE
	@Field(2) 
	public ValuedEnum<D2D1_BITMAP_INTERPOLATION_MODE > interpolationMode() {
		return this.io.getEnumField(this, 2);
	}
	/// C type : D2D1_BITMAP_INTERPOLATION_MODE
	@Field(2) 
	public D2D1_BITMAP_BRUSH_PROPERTIES interpolationMode(ValuedEnum<D2D1_BITMAP_INTERPOLATION_MODE > interpolationMode) {
		this.io.setEnumField(this, 2, interpolationMode);
		return this;
	}
}
