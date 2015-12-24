// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.eagle.programmar.EagleLanguage;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.DefinitionInterface;
import com.eagle.tokens.EagleScope;
import com.eagle.tokens.EagleScope.EagleScopeInterface;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TerminalToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.utils.EagleFile;

public class EagleReadXML implements ErrorHandler
{
	public static final String CLASS_PREFIX = "com.eagle.programmar";

	static final String XML_MAIN = "EagleProgram";
	static final String XML_CREATED = "Created";
	static final String XML_LANGUAGE = "Language";
	static final String XML_TOKENS = "Tokens";
	static final String XML_ELAPSED = "Elapsed";
	static final String XML_STEPS = "Steps";
	
	public static final String XML_TOKEN = "T";
	static final String XML_TOKENTYPE = "TT";
	static final String XML_FILENAME = "File";
	static final String XML_STARTLINE = "SL";
	static final String XML_STARTCHAR = "SC";
	static final String XML_ENDLINE = "EL";
	static final String XML_ENDCHAR = "EC";
	
	static final String XML_VALUE = "V";
	static final String XML_NAME = "N";
	static final String XML_ARRAY = "List";
	
	public int _tokens;		// For counting tokens per file
	public int _elapsed;
	public int _steps;
	
	public class XMLToken
	{
		public String _name;
		public String _type;
		public int _sl;
		public int _sc;
		public int _el;
		public int _ec;
		public String _value;
	}
	
	/**
	 * Create an XML file from a Language or GenericToken
	 * @param token - reads this GenericToken
	 * @param xmlFile - writes this file
	 * @return false only for strange errors, like directory does not exist
	 */

	//
	// Stupid XML parser doesn't know how to throw exceptions
	//
	
	private SAXParseException _parseException;
	
	@Override
	public void error(SAXParseException ex) throws SAXException
	{
		_parseException = ex;
	}

	@Override
	public void fatalError(SAXParseException ex) throws SAXException
	{
		_parseException = ex;
	}

	@Override
	public void warning(SAXParseException ex) throws SAXException
	{
		_parseException = ex;
	}
	
	public EagleLanguage readFrom(String xmlFile)
	{
		return collectStats(xmlFile, null);
	}
	
	public EagleLanguage collectStats(String xmlFile, ArrayList<XMLToken> xmlTokens)
	{
		AbstractToken token = null;
		
		Document doc = null;
		try
		{
			// Read the whole XML file into 'doc'
	    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder builder = factory.newDocumentBuilder();
	    	builder.setErrorHandler(this);
	    	
	    	_parseException = null;
	    	doc = builder.parse(new FileInputStream(xmlFile));
		}
		catch (Exception ex)
		{
			throw new RuntimeException("Unable to read " + xmlFile, ex);
		}
    	if (_parseException != null)
    	{
    		throw new RuntimeException("XML reader failed for " + xmlFile, _parseException);
    	}
    	
    	// Verify my special header
        NodeList topLevelNodeList = doc.getChildNodes();
        if (topLevelNodeList.getLength() != 1)                   
        {
            throw new RuntimeException("Invalid xml file: " + xmlFile);
        }
    
        Element mainElt = (Element) topLevelNodeList.item(0);            
        if (!mainElt.getNodeName().equalsIgnoreCase(XML_MAIN))                   
        {
            throw new RuntimeException("XML file is not a parsed program: " + xmlFile);
        }
        
        NodeList programNodeList = mainElt.getChildNodes();
        if (programNodeList.getLength() < 1)
        {
            throw new RuntimeException("Invalid program xml file: " + xmlFile);
        }

    	_elapsed = Integer.parseInt(mainElt.getAttribute(XML_ELAPSED));
    	_tokens = Integer.parseInt(mainElt.getAttribute(XML_TOKENS));
    	_steps = Integer.parseInt(mainElt.getAttribute(XML_STEPS));

        try
        {
        	for (int i = 0; i < programNodeList.getLength(); i++)
        	{
	        	Node node = programNodeList.item(i);
	        	if (node instanceof Element)
	        	{
		        	Element element = (Element) node;
		        	token = extractToken(null, element, xmlTokens);
		        	break;
	        	}
        	}
        }
        catch (Exception ex)
        {
        	throw new RuntimeException("Trouble reading " + xmlFile + ": " + ex.getMessage(), ex);
        }
        
        EagleLanguage lang = (EagleLanguage) token;
        return lang;
	}
	
	private AbstractToken extractToken(AbstractToken container, Element parent, ArrayList<XMLToken> xmlTokens) throws Exception
	{
		AbstractToken token = null;
		String clsName = parent.getAttribute(XML_TOKENTYPE);
		String fullName = clsName;
		if (clsName.startsWith(".")) fullName = CLASS_PREFIX + clsName;
		Class<?> cls = null;
		Constructor<?> construct = null;
		
		//System.out.println("******* FULLNAME = " + fullName);
		if (fullName.equalsIgnoreCase(XML_ARRAY))
		{
			// Read an array of stuff from the document tree
			String fldName = parent.getAttribute(XML_NAME);
			Field fld = container.getClass().getField(fldName);
			@SuppressWarnings("unchecked")
			Class<? extends AbstractToken> fldType = (Class<? extends AbstractToken>) fld.getType();
			boolean isSeparatedList = fldType.equals(SeparatedList.class);
			
			TokenList<AbstractToken> items;
			if (isSeparatedList)
			{
				@SuppressWarnings("unchecked")
				SeparatedList<AbstractToken, AbstractToken> items2 = (SeparatedList<AbstractToken, AbstractToken>) fld.get(container);
				items = items2;
				if (items == null)
				{
					//System.out.println("******** Creating a new TokenList");
					items = new SeparatedList<AbstractToken, AbstractToken>();
					items._present = true;
					fld.set(container, items);
				}
				//else System.out.println("********* Reusing old TokenList");
			}
			else
			{
				@SuppressWarnings("unchecked")
				TokenList<AbstractToken> items1 = (TokenList<AbstractToken>) fld.get(container);
				items = items1;
				if (items == null)
				{
					//System.out.println("******** Creating a new TokenList");
					items = new TokenList<AbstractToken>();
					items._present = true;
					fld.set(container, items);
				}
				//else System.out.println("********* Reusing old TokenList");
			}
			
			items.setParent(container);
			items._currentLine = getInt(parent, XML_STARTLINE);
			items._currentChar = getInt(parent, XML_STARTCHAR);
			items._endLine = getInt(parent ,XML_ENDLINE);
			items._endChar = getInt(parent, XML_ENDCHAR);
			//System.out.println("************* LIST " + fldName + " at (" + (items._currentLine+1) + ", " + (items._currentChar+1) + ")");

			NodeList nodes = parent.getChildNodes();
			boolean first = true;
			int saveLineNumber = 0;
			int saveCharNumber = 0;
			for (int i = 0; i < nodes.getLength(); i++)
			{
				Node node = nodes.item(i);
				if (node instanceof Element)
				{
					Element elt = (Element) node;
					//System.out.println("***************** i = " + i);
					AbstractToken child = extractToken(items, elt, xmlTokens);
					child._present = true;
					child.setParent(items);
					items.addToken(child);
					
					saveLineNumber = child._endLine;
					saveCharNumber = child._endChar;
					if (first)
					{
						items._currentLine = child._currentLine;
						items._currentChar = child._currentChar;
						first = false;
					}
				}
			}
			items._endLine = saveLineNumber;
			items._endChar = saveCharNumber;
			
			//System.out.println("**************** EXITING");
			return container;
		}

		cls = Class.forName(fullName);
		construct = cls.getConstructor((Class<?>[]) null);
		token = (AbstractToken) construct.newInstance();
		token.setParent(container);
		String val = "";
		
		// Set the value, if it is a terminal
		if (TerminalToken.class.isAssignableFrom(cls))
		{
			val = parent.getAttribute(XML_VALUE);
			((TerminalToken)token).setValue(val);
		}
		else if (TokenChooser.class.isAssignableFrom(cls))
		{
			NodeList nodes = parent.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++)
			{
				Node node = nodes.item(i);
				if (node instanceof Element)
				{
					Element elt = (Element) node;
					AbstractToken child = extractToken(token, elt, xmlTokens);
					((TokenChooser) token)._whichToken = child;
					child.setParent(token);
				}
			}
		}
		else if (TokenSequence.class.isAssignableFrom(cls))
		{
			NodeList nodes = parent.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++)
			{
				Node node = nodes.item(i);
				if (node instanceof Element)
				{
					Element elt = (Element) node;
					AbstractToken child = extractToken(token, elt, xmlTokens);
					String fldName = elt.getAttribute(XML_NAME);
					Field fld = cls.getField(fldName);
					if (token != child) fld.set(token, child);
				}
			}
		}
		else
		{
			throw new RuntimeException("Unexpected token type: " + cls.getName());
		}
		
		// Copy over the easy stuff
		if (token instanceof EagleLanguage)
		{
			token._fileName = parent.getAttribute(XML_FILENAME);
		}
		token._present = true;
		token._currentLine = getInt(parent, XML_STARTLINE);
		token._currentChar = getInt(parent, XML_STARTCHAR);
		token._endLine = getInt(parent, XML_ENDLINE);
		token._endChar = getInt(parent, XML_ENDCHAR);

//		if (token instanceof EagleScopeInterface)
//		{
//			EagleScope scope = ((EagleScopeInterface) token).getScope();
//			((EagleScopeInterface) token).setScope(scope);
//		}
		
		if (token instanceof DefinitionInterface)
		{
			AbstractToken dad = token;
			while (dad != null)
			{
				if (dad instanceof EagleScopeInterface) break;
				dad = dad.getParent();
			}
			if (dad == null)
			{
				throw new RuntimeException("Unable to finx XML scope for " + token.toString());
			}
			DefinitionInterface def = (DefinitionInterface) token;
			EagleScope scope = ((EagleScopeInterface) dad).getScope();
			scope.addSymbol(def);
		}
		
		if (xmlTokens != null)
		{
			// Collect the statistics for this token
			XMLToken xmlToken = new XMLToken();
			xmlToken._name = parent.getAttribute(XML_NAME);
			xmlToken._type = clsName;
			xmlToken._value = EagleFile.doubleQ(val);
			xmlToken._sl = token._currentLine;
			xmlToken._sc = token._currentChar;
			xmlToken._el = token._endLine;
			xmlToken._ec = token._endChar;
			
			xmlTokens.add(xmlToken);
		}
		
		return token;
	}
	
	private static int getInt(Element parent, String attribute)
	{
		String str = parent.getAttribute(attribute);
		if (str == null || str.length() == 0) return 0;
		return Integer.parseInt(str) - 1;
	}
}
