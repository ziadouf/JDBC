package jdbc;
 
import java.lang.reflect.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
 
public class Resultset {
	private int currentRow ;
	private ArrayList < ArrayList <Object> > data;
//	private Statement statement ;
	boolean isClosed = false ;
 
	Resultset(ArrayList < ArrayList <Object> > data  /* , String statement*/)
	{
		currentRow = -1 ;
		this.data = data ;
//		this.statement = statement ;
 
	}
 
	public boolean absolute(int row) throws SQLException
	{
		if(isClosed) throw new SQLException();
		row--;
		int flag = 0 ;
		if (row > data.size() || row + data.size()+1 < -1)
		{
			flag=1;
			if(row > data.size()) currentRow = data.size();
			else if(row + data.size()+1 < -1) currentRow = -1 ;
		}
		else if(row < -1) currentRow = row + data.size()+1;
		else currentRow = row ;
		if(flag==1 || currentRow == -1 || currentRow == data.size()) return false ;
		else return true ;
	}
 
	public void afterLast() throws SQLException
	{
		if(isClosed) throw new SQLException();
		currentRow = data.size();
	}
 
	public void beforeLast() throws SQLException
	{
		if(isClosed) throw new SQLException();
		currentRow = -1;
	}
	
	public void close()
	{
		isClosed = true ;
	}
	
	public boolean first() throws SQLException
	{
		if(isClosed) throw new SQLException();
		if(data.size()==0) return false;
		else
		{
			currentRow = 0 ;
			return true ;
		}
	}
	
	public boolean last() throws SQLException
	{
		if(isClosed) throw new SQLException();
		if(data.size()==0) return false;
		else
		{
			currentRow = data.size()-1 ;
			return true ;
		}
	}
	
	public Array getArray(int columnIndex) throws SQLException
	{
		columnIndex-- ;
		if( isClosed || currentRow < 0 || currentRow >= data.size() ) throw new SQLException();
		if( columnIndex < 0 || columnIndex >= data.get(currentRow).size() ) throw new SQLException() ;
		return (Array)data.get(currentRow).get(columnIndex);
		
	}
	
	public boolean getBoolean(int columnIndex) throws SQLException
	{
		columnIndex-- ;
		if( isClosed || currentRow < 0 || currentRow >= data.size() ) throw new SQLException();
		if( columnIndex < 0 || columnIndex >= data.get(currentRow).size() ) throw new SQLException() ;
		if (data.get(currentRow).get(columnIndex) == null) return false;
		return (boolean)data.get(currentRow).get(columnIndex);
		
	}
	
	public Date getDate(int columnIndex) throws SQLException
	{
		columnIndex-- ;
		if( isClosed || currentRow < 0 || currentRow >= data.size() ) throw new SQLException();
		if( columnIndex < 0 || columnIndex >= data.get(currentRow).size() ) throw new SQLException() ;
		if (data.get(currentRow).get(columnIndex) == null) return null;
		return (Date)data.get(currentRow).get(columnIndex);
		
	}
	
	public double getDouble(int columnIndex) throws SQLException
	{
		columnIndex-- ;
		if( isClosed || currentRow < 0 || currentRow >= data.size() ) throw new SQLException();
		if( columnIndex < 0 || columnIndex >= data.get(currentRow).size() ) throw new SQLException() ;
		if (data.get(currentRow).get(columnIndex) == null) return (double) 0;
		return (double)data.get(currentRow).get(columnIndex);
		
	}
	
	public float getFloat(int columnIndex) throws SQLException
	{
		columnIndex-- ;
		if( isClosed || currentRow < 0 || currentRow >= data.size() ) throw new SQLException();
		if( columnIndex < 0 || columnIndex >= data.get(currentRow).size() ) throw new SQLException() ;
		if (data.get(currentRow).get(columnIndex) == null) return (float) 0;
		return (float)data.get(currentRow).get(columnIndex);
		
	}
	
	public int getInt(int columnIndex) throws SQLException
	{
		columnIndex-- ;
		if( isClosed || currentRow < 0 || currentRow >= data.size() ) throw new SQLException();
		if( columnIndex < 0 || columnIndex >= data.get(currentRow).size() ) throw new SQLException() ;
		if (data.get(currentRow).get(columnIndex) == null) return 0;
		return (int)data.get(currentRow).get(columnIndex);
		
	}
	
	public long getLong(int columnIndex) throws SQLException
	{
		columnIndex-- ;
		if( isClosed || currentRow < 0 || currentRow >= data.size() ) throw new SQLException();
		if( columnIndex < 0 || columnIndex >= data.get(currentRow).size() ) throw new SQLException() ;
		if (data.get(currentRow).get(columnIndex) == null) return (long)0;
		return (long)data.get(currentRow).get(columnIndex);
		
	}
	
	public Object getObject(int columnIndex) throws SQLException
	{
		columnIndex-- ;
		if( isClosed || currentRow < 0 || currentRow >= data.size() ) throw new SQLException();
		if( columnIndex < 0 || columnIndex >= data.get(currentRow).size() ) throw new SQLException() ;
		return (Object)data.get(currentRow).get(columnIndex);
		
	}
	
	/*public Statement getStatement()() throws SQLException
	{
		return statement ;
		
	}*/
	
	public String getStirng(int columnIndex) throws SQLException
	{
		columnIndex-- ;
		if( isClosed || currentRow < 0 || currentRow >= data.size() ) throw new SQLException();
		if( columnIndex < 0 || columnIndex >= data.get(currentRow).size() ) throw new SQLException() ;
		if (data.get(currentRow).get(columnIndex) == null) return null;
		return (String)data.get(currentRow).get(columnIndex);
		
	}
	
	public boolean isAfterLast () throws SQLException
	{
		if( isClosed || currentRow < 0 || currentRow >= data.size() ) throw new SQLException();
		if(currentRow == data.size()) return true ;
		else return false ;
	}
	
	public boolean isBeforeFirst () throws SQLException
	{
		if( isClosed || currentRow < 0 || currentRow >= data.size() ) throw new SQLException();
		if(currentRow == -1) return true ;
		else return false ;
	}
	
	public boolean isClosed () throws SQLException
	{
		if(currentRow < 0 || currentRow >= data.size() ) throw new SQLException();
		if(isClosed) return true ;
		else return false ;
	}
	
	public boolean isFirst () throws SQLException
	{
		if( isClosed || currentRow < 0 || currentRow >= data.size() ) throw new SQLException();
		if(currentRow == 0) return true ;
		else return false ;
	}
	
	public boolean isLast () throws SQLException
	{
		if( isClosed || currentRow < 0 || currentRow >= data.size() ) throw new SQLException();
		if(currentRow == data.size()-1) return true ;
		else return false ;
	}
	
	public boolean next() throws SQLException
	{
		if( isClosed || currentRow >= data.size() ) throw new SQLException();
		if(data.size()==0)
		{
			currentRow = 0 ;
			return false ;
		}
		else
		{
			currentRow ++ ;
			return true ;
		}
	}
	
	public boolean previous() throws SQLException
	{
		if( isClosed || currentRow < 0) throw new SQLException();
		if(data.size()==0)
		{
			currentRow = -1 ;
			return false ;
		}
		else
		{
			currentRow -- ;
			return true ;
		}
	}
 
}