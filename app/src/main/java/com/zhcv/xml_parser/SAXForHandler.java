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
	private String perTag;//通过此变量，记录前一个标签的名称
	Person person;//记录当前Person
    public List<Person> getPersons(){
    	return persons;
    }
    /**
     * ch:当前读取到的TextNode字节数组；
     * start：字节开始的位置，如果要读取全部，那就要从0开始；
     * length：当前TextNode的长度。
     * 关于要获取当前TextNode，可以使用new String(ch,start,length).trim().这里最好对得到的字符串使用 trim()方法过滤一下，可以避免
     * 得到的XML有空格时，因为格式不整齐造成不必要的麻烦。
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
 * startDocument事件，该事件只有在开始解析文档时执行一次比较适合处理一些初始化的行为。
 */
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		persons = new ArrayList<Person>();
		Log.i(TAG, "***startDocument***");
	}
/**
 * 示例：<newii:will>
 * uri:命名空间，即实例中的newii；
 * localName:标签名称，既示例中的will；
 * qName:带命名空间的标签名，即示例中的newii:will；
 * Attributes:存放该标签的所有属性.
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