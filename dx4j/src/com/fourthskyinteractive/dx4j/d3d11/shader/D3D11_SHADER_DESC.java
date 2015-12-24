package com.fourthskyinteractive.dx4j.d3d11.shader;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ValuedEnum;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D_PRIMITIVE;
import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D_PRIMITIVE_TOPOLOGY;
import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D_TESSELLATOR_DOMAIN;
import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D_TESSELLATOR_OUTPUT_PRIMITIVE;
import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D_TESSELLATOR_PARTITIONING;
/**
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.free.fr/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("d3dcompiler") 
public class D3D11_SHADER_DESC extends StructObject {
	public D3D11_SHADER_DESC() {
		super();
	}
	public D3D11_SHADER_DESC(Pointer<? extends StructObject> pointer) {
		super(pointer);
	}
	/// Shader version
	@Field(0) 
	public int Version() {
		return this.io.getIntField(this, 0);
	}
	/// Shader version
	@Field(0) 
	public D3D11_SHADER_DESC Version(int Version) {
		this.io.setIntField(this, 0, Version);
		return this;
	}
	/**
	 * Creator string<br>
	 * C type : LPCSTR
	 */
	@Field(1) 
	public Pointer<Byte > Creator() {
		return this.io.getPointerField(this, 1);
	}
	/**
	 * Creator string<br>
	 * C type : LPCSTR
	 */
	@Field(1) 
	public D3D11_SHADER_DESC Creator(Pointer<Byte > Creator) {
		this.io.setPointerField(this, 1, Creator);
		return this;
	}
	/// Shader compilation/parse flags
	@Field(2) 
	public int Flags() {
		return this.io.getIntField(this, 2);
	}
	/// Shader compilation/parse flags
	@Field(2) 
	public D3D11_SHADER_DESC Flags(int Flags) {
		this.io.setIntField(this, 2, Flags);
		return this;
	}
	/// Number of constant buffers
	@Field(3) 
	public int ConstantBuffers() {
		return this.io.getIntField(this, 3);
	}
	/// Number of constant buffers
	@Field(3) 
	public D3D11_SHADER_DESC ConstantBuffers(int ConstantBuffers) {
		this.io.setIntField(this, 3, ConstantBuffers);
		return this;
	}
	/// Number of bound resources
	@Field(4) 
	public int BoundResources() {
		return this.io.getIntField(this, 4);
	}
	/// Number of bound resources
	@Field(4) 
	public D3D11_SHADER_DESC BoundResources(int BoundResources) {
		this.io.setIntField(this, 4, BoundResources);
		return this;
	}
	/// Number of parameters in the input signature
	@Field(5) 
	public int InputParameters() {
		return this.io.getIntField(this, 5);
	}
	/// Number of parameters in the input signature
	@Field(5) 
	public D3D11_SHADER_DESC InputParameters(int InputParameters) {
		this.io.setIntField(this, 5, InputParameters);
		return this;
	}
	/// Number of parameters in the output signature
	@Field(6) 
	public int OutputParameters() {
		return this.io.getIntField(this, 6);
	}
	/// Number of parameters in the output signature
	@Field(6) 
	public D3D11_SHADER_DESC OutputParameters(int OutputParameters) {
		this.io.setIntField(this, 6, OutputParameters);
		return this;
	}
	/// Number of emitted instructions
	@Field(7) 
	public int InstructionCount() {
		return this.io.getIntField(this, 7);
	}
	/// Number of emitted instructions
	@Field(7) 
	public D3D11_SHADER_DESC InstructionCount(int InstructionCount) {
		this.io.setIntField(this, 7, InstructionCount);
		return this;
	}
	/// Number of temporary registers used
	@Field(8) 
	public int TempRegisterCount() {
		return this.io.getIntField(this, 8);
	}
	/// Number of temporary registers used
	@Field(8) 
	public D3D11_SHADER_DESC TempRegisterCount(int TempRegisterCount) {
		this.io.setIntField(this, 8, TempRegisterCount);
		return this;
	}
	/// Number of temporary arrays used
	@Field(9) 
	public int TempArrayCount() {
		return this.io.getIntField(this, 9);
	}
	/// Number of temporary arrays used
	@Field(9) 
	public D3D11_SHADER_DESC TempArrayCount(int TempArrayCount) {
		this.io.setIntField(this, 9, TempArrayCount);
		return this;
	}
	/// Number of constant defines
	@Field(10) 
	public int DefCount() {
		return this.io.getIntField(this, 10);
	}
	/// Number of constant defines
	@Field(10) 
	public D3D11_SHADER_DESC DefCount(int DefCount) {
		this.io.setIntField(this, 10, DefCount);
		return this;
	}
	/// Number of declarations (input + output)
	@Field(11) 
	public int DclCount() {
		return this.io.getIntField(this, 11);
	}
	/// Number of declarations (input + output)
	@Field(11) 
	public D3D11_SHADER_DESC DclCount(int DclCount) {
		this.io.setIntField(this, 11, DclCount);
		return this;
	}
	/// Number of non-categorized texture instructions
	@Field(12) 
	public int TextureNormalInstructions() {
		return this.io.getIntField(this, 12);
	}
	/// Number of non-categorized texture instructions
	@Field(12) 
	public D3D11_SHADER_DESC TextureNormalInstructions(int TextureNormalInstructions) {
		this.io.setIntField(this, 12, TextureNormalInstructions);
		return this;
	}
	/// Number of texture load instructions
	@Field(13) 
	public int TextureLoadInstructions() {
		return this.io.getIntField(this, 13);
	}
	/// Number of texture load instructions
	@Field(13) 
	public D3D11_SHADER_DESC TextureLoadInstructions(int TextureLoadInstructions) {
		this.io.setIntField(this, 13, TextureLoadInstructions);
		return this;
	}
	/// Number of texture comparison instructions
	@Field(14) 
	public int TextureCompInstructions() {
		return this.io.getIntField(this, 14);
	}
	/// Number of texture comparison instructions
	@Field(14) 
	public D3D11_SHADER_DESC TextureCompInstructions(int TextureCompInstructions) {
		this.io.setIntField(this, 14, TextureCompInstructions);
		return this;
	}
	/// Number of texture bias instructions
	@Field(15) 
	public int TextureBiasInstructions() {
		return this.io.getIntField(this, 15);
	}
	/// Number of texture bias instructions
	@Field(15) 
	public D3D11_SHADER_DESC TextureBiasInstructions(int TextureBiasInstructions) {
		this.io.setIntField(this, 15, TextureBiasInstructions);
		return this;
	}
	/// Number of texture gradient instructions
	@Field(16) 
	public int TextureGradientInstructions() {
		return this.io.getIntField(this, 16);
	}
	/// Number of texture gradient instructions
	@Field(16) 
	public D3D11_SHADER_DESC TextureGradientInstructions(int TextureGradientInstructions) {
		this.io.setIntField(this, 16, TextureGradientInstructions);
		return this;
	}
	/// Number of floating point arithmetic instructions used
	@Field(17) 
	public int FloatInstructionCount() {
		return this.io.getIntField(this, 17);
	}
	/// Number of floating point arithmetic instructions used
	@Field(17) 
	public D3D11_SHADER_DESC FloatInstructionCount(int FloatInstructionCount) {
		this.io.setIntField(this, 17, FloatInstructionCount);
		return this;
	}
	/// Number of signed integer arithmetic instructions used
	@Field(18) 
	public int IntInstructionCount() {
		return this.io.getIntField(this, 18);
	}
	/// Number of signed integer arithmetic instructions used
	@Field(18) 
	public D3D11_SHADER_DESC IntInstructionCount(int IntInstructionCount) {
		this.io.setIntField(this, 18, IntInstructionCount);
		return this;
	}
	/// Number of unsigned integer arithmetic instructions used
	@Field(19) 
	public int UintInstructionCount() {
		return this.io.getIntField(this, 19);
	}
	/// Number of unsigned integer arithmetic instructions used
	@Field(19) 
	public D3D11_SHADER_DESC UintInstructionCount(int UintInstructionCount) {
		this.io.setIntField(this, 19, UintInstructionCount);
		return this;
	}
	/// Number of static flow control instructions used
	@Field(20) 
	public int StaticFlowControlCount() {
		return this.io.getIntField(this, 20);
	}
	/// Number of static flow control instructions used
	@Field(20) 
	public D3D11_SHADER_DESC StaticFlowControlCount(int StaticFlowControlCount) {
		this.io.setIntField(this, 20, StaticFlowControlCount);
		return this;
	}
	/// Number of dynamic flow control instructions used
	@Field(21) 
	public int DynamicFlowControlCount() {
		return this.io.getIntField(this, 21);
	}
	/// Number of dynamic flow control instructions used
	@Field(21) 
	public D3D11_SHADER_DESC DynamicFlowControlCount(int DynamicFlowControlCount) {
		this.io.setIntField(this, 21, DynamicFlowControlCount);
		return this;
	}
	/// Number of macro instructions used
	@Field(22) 
	public int MacroInstructionCount() {
		return this.io.getIntField(this, 22);
	}
	/// Number of macro instructions used
	@Field(22) 
	public D3D11_SHADER_DESC MacroInstructionCount(int MacroInstructionCount) {
		this.io.setIntField(this, 22, MacroInstructionCount);
		return this;
	}
	/// Number of array instructions used
	@Field(23) 
	public int ArrayInstructionCount() {
		return this.io.getIntField(this, 23);
	}
	/// Number of array instructions used
	@Field(23) 
	public D3D11_SHADER_DESC ArrayInstructionCount(int ArrayInstructionCount) {
		this.io.setIntField(this, 23, ArrayInstructionCount);
		return this;
	}
	/// Number of cut instructions used
	@Field(24) 
	public int CutInstructionCount() {
		return this.io.getIntField(this, 24);
	}
	/// Number of cut instructions used
	@Field(24) 
	public D3D11_SHADER_DESC CutInstructionCount(int CutInstructionCount) {
		this.io.setIntField(this, 24, CutInstructionCount);
		return this;
	}
	/// Number of emit instructions used
	@Field(25) 
	public int EmitInstructionCount() {
		return this.io.getIntField(this, 25);
	}
	/// Number of emit instructions used
	@Field(25) 
	public D3D11_SHADER_DESC EmitInstructionCount(int EmitInstructionCount) {
		this.io.setIntField(this, 25, EmitInstructionCount);
		return this;
	}
	/**
	 * Geometry shader output topology<br>
	 * C type : D3D_PRIMITIVE_TOPOLOGY
	 */
	@Field(26) 
	public ValuedEnum<D3D_PRIMITIVE_TOPOLOGY > GSOutputTopology() {
		return this.io.getEnumField(this, 26);
	}
	/**
	 * Geometry shader output topology<br>
	 * C type : D3D_PRIMITIVE_TOPOLOGY
	 */
	@Field(26) 
	public D3D11_SHADER_DESC GSOutputTopology(ValuedEnum<D3D_PRIMITIVE_TOPOLOGY > GSOutputTopology) {
		this.io.setEnumField(this, 26, GSOutputTopology);
		return this;
	}
	/// Geometry shader maximum output vertex count
	@Field(27) 
	public int GSMaxOutputVertexCount() {
		return this.io.getIntField(this, 27);
	}
	/// Geometry shader maximum output vertex count
	@Field(27) 
	public D3D11_SHADER_DESC GSMaxOutputVertexCount(int GSMaxOutputVertexCount) {
		this.io.setIntField(this, 27, GSMaxOutputVertexCount);
		return this;
	}
	/**
	 * GS/HS input primitive<br>
	 * C type : D3D_PRIMITIVE
	 */
	@Field(28) 
	public ValuedEnum<D3D_PRIMITIVE > InputPrimitive() {
		return this.io.getEnumField(this, 28);
	}
	/**
	 * GS/HS input primitive<br>
	 * C type : D3D_PRIMITIVE
	 */
	@Field(28) 
	public D3D11_SHADER_DESC InputPrimitive(ValuedEnum<D3D_PRIMITIVE > InputPrimitive) {
		this.io.setEnumField(this, 28, InputPrimitive);
		return this;
	}
	/// Number of parameters in the patch constant signature
	@Field(29) 
	public int PatchConstantParameters() {
		return this.io.getIntField(this, 29);
	}
	/// Number of parameters in the patch constant signature
	@Field(29) 
	public D3D11_SHADER_DESC PatchConstantParameters(int PatchConstantParameters) {
		this.io.setIntField(this, 29, PatchConstantParameters);
		return this;
	}
	/// Number of Geometry shader instances
	@Field(30) 
	public int cGSInstanceCount() {
		return this.io.getIntField(this, 30);
	}
	/// Number of Geometry shader instances
	@Field(30) 
	public D3D11_SHADER_DESC cGSInstanceCount(int cGSInstanceCount) {
		this.io.setIntField(this, 30, cGSInstanceCount);
		return this;
	}
	/// Number of control points in the HS->DS stage
	@Field(31) 
	public int cControlPoints() {
		return this.io.getIntField(this, 31);
	}
	/// Number of control points in the HS->DS stage
	@Field(31) 
	public D3D11_SHADER_DESC cControlPoints(int cControlPoints) {
		this.io.setIntField(this, 31, cControlPoints);
		return this;
	}
	/**
	 * Primitive output by the tessellator<br>
	 * C type : D3D_TESSELLATOR_OUTPUT_PRIMITIVE
	 */
	@Field(32) 
	public ValuedEnum<D3D_TESSELLATOR_OUTPUT_PRIMITIVE > HSOutputPrimitive() {
		return this.io.getEnumField(this, 32);
	}
	/**
	 * Primitive output by the tessellator<br>
	 * C type : D3D_TESSELLATOR_OUTPUT_PRIMITIVE
	 */
	@Field(32) 
	public D3D11_SHADER_DESC HSOutputPrimitive(ValuedEnum<D3D_TESSELLATOR_OUTPUT_PRIMITIVE > HSOutputPrimitive) {
		this.io.setEnumField(this, 32, HSOutputPrimitive);
		return this;
	}
	/**
	 * Partitioning mode of the tessellator<br>
	 * C type : D3D_TESSELLATOR_PARTITIONING
	 */
	@Field(33) 
	public ValuedEnum<D3D_TESSELLATOR_PARTITIONING > HSPartitioning() {
		return this.io.getEnumField(this, 33);
	}
	/**
	 * Partitioning mode of the tessellator<br>
	 * C type : D3D_TESSELLATOR_PARTITIONING
	 */
	@Field(33) 
	public D3D11_SHADER_DESC HSPartitioning(ValuedEnum<D3D_TESSELLATOR_PARTITIONING > HSPartitioning) {
		this.io.setEnumField(this, 33, HSPartitioning);
		return this;
	}
	/**
	 * Domain of the tessellator (quad, tri, isoline)<br>
	 * C type : D3D_TESSELLATOR_DOMAIN
	 */
	@Field(34) 
	public ValuedEnum<D3D_TESSELLATOR_DOMAIN > TessellatorDomain() {
		return this.io.getEnumField(this, 34);
	}
	/**
	 * Domain of the tessellator (quad, tri, isoline)<br>
	 * C type : D3D_TESSELLATOR_DOMAIN
	 */
	@Field(34) 
	public D3D11_SHADER_DESC TessellatorDomain(ValuedEnum<D3D_TESSELLATOR_DOMAIN > TessellatorDomain) {
		this.io.setEnumField(this, 34, TessellatorDomain);
		return this;
	}
	/**
	 * instruction counts<br>
	 * Number of barrier instructions in a compute shader
	 */
	@Field(35) 
	public int cBarrierInstructions() {
		return this.io.getIntField(this, 35);
	}
	/**
	 * instruction counts<br>
	 * Number of barrier instructions in a compute shader
	 */
	@Field(35) 
	public D3D11_SHADER_DESC cBarrierInstructions(int cBarrierInstructions) {
		this.io.setIntField(this, 35, cBarrierInstructions);
		return this;
	}
	/// Number of interlocked instructions
	@Field(36) 
	public int cInterlockedInstructions() {
		return this.io.getIntField(this, 36);
	}
	/// Number of interlocked instructions
	@Field(36) 
	public D3D11_SHADER_DESC cInterlockedInstructions(int cInterlockedInstructions) {
		this.io.setIntField(this, 36, cInterlockedInstructions);
		return this;
	}
	/// Number of texture writes
	@Field(37) 
	public int cTextureStoreInstructions() {
		return this.io.getIntField(this, 37);
	}
	/// Number of texture writes
	@Field(37) 
	public D3D11_SHADER_DESC cTextureStoreInstructions(int cTextureStoreInstructions) {
		this.io.setIntField(this, 37, cTextureStoreInstructions);
		return this;
	}
}

