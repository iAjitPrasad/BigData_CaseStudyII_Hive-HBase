package FirstAPI;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class scanHBase 
{
	public static void main(String args[]) throws IOException
	{
		
		HBaseConfiguration conf = new HBaseConfiguration();
		
		@SuppressWarnings("deprecation")
		
		HTable htable = new HTable(conf,"Transcations");
		Scan scan = new Scan();
		
		ResultScanner scanner = htable.getScanner(scan);
        Result r;
		
		while (((r = scanner.next()) != null)) 
		{
			
            String userId = Bytes.toString(r.getRow()); 
    	    String name = Bytes.toString(r.getValue("details".getBytes(),"name".getBytes()));
            String count = Bytes.toString(r.getValue(Bytes.toBytes("details"), Bytes.toBytes("txn_count")));


            System.out.println("RowKey: " + userId + ", User Name: "+name+",  Count: " + count);
        }

        scanner.close();
        htable.close();
	}
}