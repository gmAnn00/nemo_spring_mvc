package com.springmvc.nemo.common;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SqlTimestampPropertyEditor extends PropertyEditorSupport{
	
	public static final String DEFAULT_BATCH_PATTERN = "yyyy-MM-dd";

    private final SimpleDateFormat sdf;
    
    public SqlTimestampPropertyEditor() {
    	this.sdf = new SimpleDateFormat(SqlTimestampPropertyEditor.DEFAULT_BATCH_PATTERN);
	}
    
    public SqlTimestampPropertyEditor(SimpleDateFormat format) {
    	this.sdf = format;
	}
    
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
    	try {
            setValue(new Timestamp(this.sdf.parse(text).getTime()));
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
        }
    }
    
    @Override
    public String getAsText() {
    	Timestamp value = (Timestamp) getValue();
        return (value != null ? this.sdf.format(value) : "");
    }

}
