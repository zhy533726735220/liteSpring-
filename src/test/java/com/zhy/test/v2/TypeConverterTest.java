package com.zhy.test.v2;


import com.zhy.beans.SimpleTypeConverter;
import com.zhy.beans.TypeConverter;
import com.zhy.beans.TypeMismatchException;
import org.junit.Assert;
import org.junit.Test;

public class TypeConverterTest {
    @Test
    public void testConvertStringToInt() {
        TypeConverter converter = new SimpleTypeConverter();

        try{
            Integer i = converter.convertIfNecessary("3", Integer.class);
            Assert.assertEquals(3,i.intValue());
            converter.convertIfNecessary("3.1", Integer.class);
        }catch(TypeMismatchException e){
            return;
        }
        Assert.fail();
    }
    @Test
    public void testConvertStringToBoolean(){
        TypeConverter converter = new SimpleTypeConverter();

        try{
            Boolean b = converter.convertIfNecessary("true", Boolean.class);
            Assert.assertEquals(true,b.booleanValue());
            converter.convertIfNecessary("xxxyyyzzz", Boolean.class);
        }catch(TypeMismatchException e){
            return;
        }
        Assert.fail();
    }

}
