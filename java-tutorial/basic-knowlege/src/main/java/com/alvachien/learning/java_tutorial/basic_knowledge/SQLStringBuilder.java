package com.alvachien.learning.java_tutorial.basic_knowledge;

public class SQLStringBuilder {
    public String BuildInsertString(String table, String[] fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ")
            .append(table)
            .append(" (")
            .append(String.join(",", fields))
            .append(") VALUES (");
        String[] fvals = new String[fields.length];
        for(int i = 0; i < fields.length; i++)
            fvals[i] = "?";
        sb.append(String.join(",", fvals))
            .append(")");
        
        // for(String field: fields) {
        //     sb.append(field)
        //         .append(",");
        // }

        return sb.toString();
    }
}
