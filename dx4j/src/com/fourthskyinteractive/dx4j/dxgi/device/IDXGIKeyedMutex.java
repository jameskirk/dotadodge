package com.fourthskyinteractive.dx4j.dxgi.device;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

/**
 * <i>native declaration : DXGI.h:589</i><br>
 * Error: Conversion Error : uuid("aec22fb8-76f3-4639-9be0-28eb43a67a2e") novtable struct IDXGIObject {<br>
 * 	/// Original signature : <code>int SetPrivateData(const GUID&, UINT, const void*)</code><br>
 * 	virtual int SetPrivateData(const GUID& Name, UINT DataSize, const void* pData);<br>
 * 	/// Original signature : <code>int SetPrivateDataInterface(const GUID&, const IUnknown*)</code><br>
 * 	virtual int SetPrivateDataInterface(const GUID& Name, const IUnknown* pUnknown);<br>
 * 	/// Original signature : <code>int GetPrivateData(const GUID&, UINT*, void*)</code><br>
 * 	virtual int GetPrivateData(const GUID& Name, UINT* pDataSize, void* pData);<br>
 * 	/// Original signature : <code>int GetParent(const IID&, void**)</code><br>
 * 	virtual int GetParent(const IID& riid, void** ppParent);<br>
 * } (Cannot find parent IUnknown of struct uuid("aec22fb8-76f3-4639-9be0-28eb43a67a2e") novtable struct IDXGIObject {<br>
 * 	/// Original signature : <code>int SetPrivateData(const GUID&, UINT, const void*)</code><br>
 * 	virtual int SetPrivateData(const GUID& Name, UINT DataSize, const void* pData);<br>
 * 	/// Original signature : <code>int SetPrivateDataInterface(const GUID&, const IUnknown*)</code><br>
 * 	virtual int SetPrivateDataInterface(const GUID& Name, const IUnknown* pUnknown);<br>
 * 	/// Original signature : <code>int GetPrivateData(const GUID&, UINT*, void*)</code><br>
 * 	virtual int GetPrivateData(const GUID& Name, UINT* pDataSize, void* pData);<br>
 * 	/// Original signature : <code>int GetParent(const IID&, void**)</code><br>
 * 	virtual int GetParent(const IID& riid, void** ppParent);<br>
 * })<br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.free.fr/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@IID("9d8e1289-d7b3-465f-8126-250e349af85d") 
@Library("dxgi") 
@Runtime(COMRuntime.class)
public class IDXGIKeyedMutex extends IDXGIDeviceSubObject {
	public IDXGIKeyedMutex() {
		super();
	}
//	public IDXGIKeyedMutex(Pointer pointer) {
//		super(pointer);
//	}
	@Virtual(0) 
	public native int AcquireSync(long Key, int dwMilliseconds);
	@Virtual(1) 
	public native int ReleaseSync(long Key);
}
