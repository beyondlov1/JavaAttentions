

**�ж��Ƿ�ʵ����ĳ���ӿڻ��߼̳���ĳ����**
import java.io.Serializable;  
      
    public class IsAssignableFromTest implements Serializable{  
      
        /** ���к� */  
        private static final long serialVersionUID = 5716955136475665579L;  
      
        public static void main(String[] args) {  
              
            //�����Ƿ�ʵ���˸���  
            boolean re1= Object.class.isAssignableFrom(IsAssignableFromTest.class);  
            //�����Ƿ�ʵ���˽ӿ�  
            boolean re2=Serializable.class.isAssignableFrom(IsAssignableFromTest.class);  
              
            System.out.println("re1:"+re1+" re2:"+re2);  
        }  
    }  