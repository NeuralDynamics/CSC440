package app;

import java.util.List;

public interface IWriter<T>
{
	public void writeData(List<T> list);
}
