

**判断是否实现了某个接口或者继承了某个类**
import java.io.Serializable;  
      
    public class IsAssignableFromTest implements Serializable{  
      
        /** 序列号 */  
        private static final long serialVersionUID = 5716955136475665579L;  
      
        public static void main(String[] args) {  
              
            //测试是否实现了父类  
            boolean re1= Object.class.isAssignableFrom(IsAssignableFromTest.class);  
            //测试是否实现了接口  
            boolean re2=Serializable.class.isAssignableFrom(IsAssignableFromTest.class);  
              
            System.out.println("re1:"+re1+" re2:"+re2);  
        }  
    }  