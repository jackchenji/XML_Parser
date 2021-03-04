package com.zhcv.xml_parser;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.test.AndroidTestCase;
import android.util.Log;

public class PersonServiceTest extends AndroidTestCase{
	private static final String TAG = "PersonServiceTest";
	public void testSAXGetPersons()throws Throwable{
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("zhangxiaobo.xml");
		SAXForHandler saxForHandler = new SAXForHandler();
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		saxParser.parse(inputStream, saxForHandler);
		List<Person> persons = saxForHandler.getPersons();
		inputStream.close();
		for(Person person:persons){
			Log.i(TAG, person.toString());
		}
		
	}
}