package dropper.lua;

import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

public class Hello
{
  public static void main(String[] args) throws LuaException
  {
    
	LuaState L = LuaStateFactory.newLuaState();
    L.openLibs();
    //L.LloadFile("hello.lua");
    
    L.LdoFile("hello.lua");
   // L.LdoFile("dropper.lua.hello");
    L.close();
    
    
    
    System.out.println("Olá Mundo de Java!");
  }
}