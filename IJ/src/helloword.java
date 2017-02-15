import java.util.Scanner;

/**
 * Created by 超群 on 2017/2/12.
 */
public class helloword {
    public static int number_front[][];                         //前面位置的数字
    public static int number_behind[][];                        //后面位置的数字
    public static int answer[][];                               //计算机计算出的答案
    public static int input_answer[][];                         //用户输入的答案
    public  static char character[];                            //将用户输入的字符串分解成单个字符
    public static int symbol[];                                 //算式符号集
    public static int flag=0;                                   //标记当前进度，做到第几题了
    public static int amount_of_right=0;                        //答题正确的数量
    public static void main(String[] args) {
        System.out.println("小学生你好");
        number_front=new int[100][2];
        number_behind=new int[100][2];
        answer=new int[100][2];
        symbol=new int[10];
        input_answer=new int[100][2];
        int zui_da_gong_yue_shu;                                    //最大公约数
        Scanner in=new Scanner(System.in);

        for(int i=0;i<10;i++){                                     //随机产生10道四则运算式
            number_front[i][1]=1+(int)(Math.random()*100);                      //前面位置的数字的产生及约分
            number_front[i][0]=1+(int)(Math.random()*number_front[i][1]);       //限制随机范围，确保产生的是真分数
            zui_da_gong_yue_shu=yuefen(number_front[i][0],number_front[i][1]);
            number_front[i][0]=number_front[i][0]/zui_da_gong_yue_shu;
            number_front[i][1]=number_front[i][1]/zui_da_gong_yue_shu;

            symbol[i]=(int)(Math.random()*4);                                   //符号的产生，0代表加，1代表减，2代表乘，3代表除

            number_behind[i][1]=1+(int)(Math.random()*10);                      //后面位置的数字的产生及约分
            number_behind[i][0]=1+(int)(Math.random()*number_behind[i][1]);     //限制随机范围，确保产生的是真分数
            zui_da_gong_yue_shu=yuefen(number_behind[i][0],number_behind[i][1]);
            number_behind[i][0]=number_behind[i][0]/zui_da_gong_yue_shu;
            number_behind[i][1]=number_behind[i][1]/zui_da_gong_yue_shu;
        }

        for(int i=0;i<10;i++){
            if(symbol[i]==0){                                   //加法运算
                answer[i][0]=number_front[i][0]*number_behind[i][1]+number_front[i][1]*number_behind[i][0];
                answer[i][1]=number_front[i][1]*number_behind[i][1];
            }
            if(symbol[i]==1){                                   //减法运算
                answer[i][0]=number_front[i][0]*number_behind[i][1]-number_front[i][1]*number_behind[i][0];
                answer[i][1]=number_front[i][1]*number_behind[i][1];
            }
            if(symbol[i]==2){                                   //乘法运算
                answer[i][0]=number_front[i][0]*number_behind[i][0];
                answer[i][1]=number_front[i][1]*number_behind[i][1];
            }
            if(symbol[i]==3){                                   //除法运算
                answer[i][0]=number_front[i][0]*number_behind[i][1];
                answer[i][1]=number_front[i][1]*number_behind[i][0];
            }
            zui_da_gong_yue_shu=yuefen(answer[i][0],answer[i][1]);         //将计算出来的答案进行约分
            answer[i][0]=answer[i][0]/zui_da_gong_yue_shu;
            answer[i][1]=answer[i][1]/zui_da_gong_yue_shu;
        }


        for(int i=0;i<10;i++) {                                 //打印算式
            if(number_front[i][0]%number_front[i][1]!=0)                //判断是否是整数
                System.out.print(number_front[i][0]+"/"+number_front[i][1]);
            else
                System.out.print(number_front[i][0]/number_front[i][1]);

            if(symbol[i]==0)
                System.out.print("+");
            else if(symbol[i]==1)
                System.out.print("-");
            else if(symbol[i]==2)
                System.out.print("*");
            else if(symbol[i]==3)
                System.out.print("÷");

            if(number_behind[i][0]%number_behind[i][1]!=0)              //判断是否是整数
                System.out.print(number_behind[i][0]+"/"+number_behind[i][1]+"=");
            else
                System.out.print(number_behind[i][0]/number_behind[i][1]+"=");

            String str=in.next();       //获取用户输入的答案字符串
            input(str);                 //通过函数将字符串分解成分子和分母并存入用户输入答案数组
            if(answer[flag][0]==input_answer[flag][0]&&answer[flag][1]==input_answer[flag][1]) {     //判断用过户输入的答案的对错
                System.out.println("√！！恭喜你答对了~~");
                amount_of_right++;                  //累计回答正确的数量
             }
            else {
                System.out.print("×！！渣渣，正确答案是");
                if(answer[flag][0]%answer[flag][1]!=0)                  //判断是否是整数
                    System.out.println(answer[flag][0]+"/"+answer[flag][1]);
                else
                    System.out.println(answer[flag][0]/answer[flag][1]);
            }
                flag++;
        }
        System.out.print("答题完毕！");
        zui_da_gong_yue_shu=yuefen(amount_of_right,10);
        if(zui_da_gong_yue_shu==10)
            System.out.println("恭喜全部答对！");
        else
            System.out.println("正确"+amount_of_right+"题，"+"正确率为"+amount_of_right/zui_da_gong_yue_shu+"/"+10/zui_da_gong_yue_shu);
    }

    public static int yuefen(int fenzi,int fenmu){     //约分函数，通过辗转相除法得到最大公约数并将该数返回
        int c;
        int a=fenzi;
        int b=fenmu;
        while(b!=0) {
            c = a % b;
            a = b;
            b = c;
        }
        if(a<0) a=-a;
        return a;
    }
    public static void input(String str){    //将用户输入的字符串分解并识别成数字
        int length;
        int location_of_fenshuxian=0;
        length=str.length();
        int g=1;
        int i;
        character=new char[10];
        for(i=0;i<length;i++){           //获得用户输入的字符串的长度
            character[i]=str.charAt(i);
        }
        for(i=0;i<length;i++){
            if(character[i]=='/'){
                location_of_fenshuxian=i;      //查找用户输入的字符串中分数线的位置
                break;
            }
        }
        if(i==length){                          //情况1：找不到分数线，输入的为代表整数的字符串
            if(character[0]=='-'){              //情况1.1：字符串首为‘-’号，输入的是负数
                input_answer[flag][0] = 0;
                for(i=1;i<length;i++){
                    input_answer[flag][0]+=(((int)(character[length-i])-48)*g);
                    g*=10;
                }
            }
            if(character[0]!='-'){              //情况1.2：字符串首没有‘-’号，输入的是正数
                input_answer[flag][0]=0;
                for(i=0;i<length;i++){
                    input_answer[flag][0]+=(((int)(character[length-1-i])-48)*g);
                    g*=10;
                }
            }
            input_answer[flag][1]=1;
        }
        else {                                   //情况2：找到了分数线，输入的为代表分数的字符串
            if (character[0] == '-') {           //情况2.1：字符串首为‘-’号，输入的是负数
                input_answer[flag][0] = 0;
                g = 1;
                for (i = 0; i < (location_of_fenshuxian - 1); i++) {
                    input_answer[flag][0] += (((int) (character[location_of_fenshuxian - i - 1]) - 48) * g);
                    g *= 10;
                }
                input_answer[flag][0] = -input_answer[flag][0];
                input_answer[flag][1] = 0;
                g = 1;
                for (i = 0; i < length - location_of_fenshuxian - 1; i++) {
                    input_answer[flag][1] += (((int) (character[length - i - 1]) - 48) * g);
                    g *= 10;
                }

            }
            if (character[0] != '-') {           //情况2.1：字符串首为‘-’号，输入的是负数
                input_answer[flag][0] = 0;
                g = 1;
                for (i = 0; i < (location_of_fenshuxian); i++) {
                    input_answer[flag][0] += ((character[location_of_fenshuxian - i - 1] - 48) * g);
                    g *= 10;
                }
                input_answer[flag][1] = 0;
                g = 1;
                for (i = 0; i < length - location_of_fenshuxian - 1; i++) {
                    input_answer[flag][1] += ((character[length - i - 1] - 48) * g);
                    g *= 10;
                }
            } else System.out.println("");
        }
    }
}
