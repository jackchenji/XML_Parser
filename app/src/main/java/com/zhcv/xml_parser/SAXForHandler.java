package com.zhcv.xml_parser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class SAXForHandler extends DefaultHandler{
	private static final String TAG = "SAXForHandler";
	private List<Person> persons;
	private String perTag;//ͨ���˱�������¼ǰһ����ǩ������
	Person person;//��¼��ǰPerson
    public List<Person> getPersons(){
    	return persons;
    }
    /**
     * ch:��ǰ��ȡ����TextNode�ֽ����飻
     * start���ֽڿ�ʼ��λ�ã����Ҫ��ȡȫ�����Ǿ�Ҫ��0��ʼ��
     * length����ǰTextNode�ĳ��ȡ�
     * ����Ҫ��ȡ��ǰTextNode������ʹ��new String(ch,start,length).trim().������öԵõ����ַ���ʹ�� trim()��������һ�£����Ա���
     * �õ���XML�пո�ʱ����Ϊ��ʽ��������ɲ���Ҫ���鷳��
     */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String data = new String(ch,start,length).trim();
		if(!"".equals(data.trim())){
			Log.i(TAG, "content: " + data.trim());
		}
		if("name".equals(perTag)){
			person.setName(data);
		}else if("age".equals(perTag)){
			person.setAge(new Short(data));
		}
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		Log.i(TAG, "***endDocument()***");
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		Log.i(TAG, qName + "***endElement***");
		if("person".equals(localName)){
			persons.add(person);
			person = null;
		}
		perTag = null;
	}
/**
 * startDocument�¼������¼�ֻ���ڿ�ʼ�����ĵ�ʱִ��һ�αȽ��ʺϴ���һЩ��ʼ������Ϊ��
 */
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		persons = new ArrayList<Person>();
		Log.i(TAG, "***startDocument***");
	}
/**
 * ʾ����<newii:will>
 * uri:�����ռ䣬��ʵ���е�newii��
 * localName:��ǩ���ƣ���ʾ���е�will��
 * qName:�������ռ�ı�ǩ������ʾ���е�newii:will��
 * Attributes:��Ÿñ�ǩ����������.
 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		if("person".equals(localName)){
			for(int i = 0;i<attributes.getLength();i++){
				Log.i(TAG, "attributeName:" + attributes.getLocalName(i)+"_attribute_Value: "+ attributes.getValue(i));
				person = new Person();
				person.setId(Integer.valueOf(attributes.getValue(i)));
			}
		}
		perTag = localName;
		Log.i(TAG, qName+"***startElement***");
	}
	
}