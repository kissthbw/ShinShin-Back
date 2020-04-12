package com.bit.model.report;
public class TestProductoReportFactory {
    //CreateArrayList
    public static ProductoReport[] generateBeanArray()
    {
    	ProductoReport[] list = new ProductoReport[2];
        list[0] = new ProductoReport( 1L, "", "Switch", "Nintendo", 0 );

        return list;
    }
    //createCollection
    public static java.util.Collection genereateCollection()
    {
        java.util.Vector collection = new java.util.Vector();
        collection.add(new ProductoReport( 1L, "", "Switch", "Nintendo", 0 ));
      
        return collection;
    }
}