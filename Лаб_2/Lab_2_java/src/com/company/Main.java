import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;

import static java.lang.Integer.MAX_VALUE;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        n=4;
        File f0 = new File("name.txt");
        polyphaseSort(counter());
    }
    public static long counter() throws FileNotFoundException {
        long count=0;
        File file = new File("name.txt");
        Scanner sc = new Scanner(file);
        int g;
        while (sc.hasNextInt()){
            g = sc.nextInt();
            count++;
        }
        return count;
    }
    public static int n,j,l;
    public static void select(int[] a, int[] d) {
        int i,a0;
        if((d[j])<(d[j+1])) j++;
        else {
            if(d[j]==0) {
                l++;
                a0=a[0];
                for (i=0;i<n-1;i++) {
                    d[i]=a0+a[i+1]-a[i];
                    a[i] = a0 + a[i + 1];
                }
            }
            j=0;
        }
        d[j] = d[j] - 1;
    }
    public static void polyphaseSort(long eof) throws IOException {
        int i, z, k, x, mx, min;
        int tn,dn;
        int[] a = new int[n];
        int[] d = new int[n];
        int[] t = new int[n];
        int[] ta = new int[n];
        File[] f = new File[n];
        File f0 = new File("name.txt");

        FileWriter[] in = new FileWriter[n];

        for(i=0;i<n-1;i++) {
            f[i] = new File("T"+(i+1)+".txt");
            f[i].createNewFile();
            in[i]= new FileWriter(f[i]);
        }
        f[n-1] = new File("T"+n+".txt");
        f[n-1].createNewFile();
        for(i=0;i<n-1;i++) {
            a[i] = 1; d[i] = 1;
        }
        l = 1; j = 0; a[n-1] = 0; d[n-1] = 0;

        BufferedReader reader = Files.newBufferedReader(f0.toPath(), StandardCharsets.UTF_8);
        boolean eor;
        int c=0;
        String firstX, firstZ;
        String[] firstY=new String[n];
        firstX = reader.readLine();
        do {
            select(a,d);
            eor=false;
//            copyrun(f0,f[j]);
            do {
//                copy();
                firstY[j]=firstX;
//                System.out.println(j+" "+firstX);
                in[j].write(firstX+'\n');
                firstX = reader.readLine();
                if(firstX!=null) {
                    if (Integer.parseInt(firstX) < Integer.parseInt(firstY[j])) eor = true;
                }
                c++;
//                System.out.println("c = "+c+" eof = "+eof);
            } while (!eor && c!=eof);//!?
        } while (c!=eof && (j!=n-2));//!?
        j=0;
//        System.out.println(c);
        while (c!=eof) {//!? //c<eof
            select(a,d);
            if(Integer.parseInt(firstY[j]) <= Integer.parseInt(firstX)) {
//            copyrun(f0,f[j]);
                eor=false;
                do {
//                copy();
                    firstY[j]=firstX;
                    in[j].write(firstX+'\n');
                    firstX = reader.readLine();
//                System.out.println("firstX = "+firstX);
                    if(firstX!=null) {
                        if (Integer.parseInt(firstX) < Integer.parseInt(firstY[j])) eor = true;
                    }
                    c++;
//                System.out.println("c = "+c+" eof = "+eof);
                } while (!eor && c!=eof);//!?
                if(c==eof) {//!?
                    d[j]=d[j]+1;
                }
                else {
//            copyrun(f0,f[j]);
                    eor = false;
                    do {
//                copy();
                        firstY[j]=firstX;
                        in[j].write(firstX+'\n');
                        firstX = reader.readLine();
//                System.out.println("firstX = "+firstX);
                        if(firstX!=null) {
                            if (Integer.parseInt(firstX) < Integer.parseInt(firstY[j])) eor = true;
                        }
                        c++;
//                System.out.println("c = "+c+" eof = "+eof);
                    } while (!eor && c!=eof);//!?
                }
            }
            else {
//            copyrun(f0,f[j]);
                eor = false;
                do {
//                copy();
                    firstY[j]=firstX;
                    in[j].write(firstX+'\n');
                    firstX = reader.readLine();
//                System.out.println("firstX = "+firstX);
                    if(firstX!=null) {
                        if (Integer.parseInt(firstX) < Integer.parseInt(firstY[j])) eor = true;
                    }
                    c++;
//                System.out.println("c = "+c+" eof = "+eof);
                } while (!eor && c!=eof);//!?
            }
        }
reader.close();
        BufferedReader[] readers = new BufferedReader[n];
        for(i=0;i<n-1;i++) {//!!!
            in[i].close();
            t[i] = i;
////            startRead();
            readers[i] = Files.newBufferedReader(f[i].toPath(), StandardCharsets.UTF_8);
        }
        t[n-1] = n-1;

//        for(i=0;i<n;i++) {
//            in[i]= new FileWriter(f[i]);
//        }
int minTmp, close;
        close=t[n-1];
        for(i=0;i<n-1;i++) {
            firstY[i]=readers[i].readLine();
        }
        do {
            in[t[n-1]]= new FileWriter(f[t[n-1]]);
            z=a[n-2]; d[n-1]=0;
//            for(i=0;i<n;i++) {
//                System.out.println(t[i]);
//            }
//            for(i=0;i<n-1;i++) {
//                firstY[i]=readers[i].readLine();
//            }
            do {
                k = 0;
                for (i = 0; i < n - 1; i++) {//!!!
                    if (d[i] > 0) {
                        d[i] = d[i] - 1;
                    } else {
                        ta[k] = t[i]; k = k + 1;
                    }
                }
                if (k == 0) {
                    d[n-1] = d[n-1] + 1;
                }
                else {
                    do {
//                        if(l==1){
//                            readers[2] = Files.newBufferedReader(f[t[close]].toPath(), StandardCharsets.UTF_8);
//                            in[t[n-1]].write(readers[2].readLine()+"");
//                            in[t[n-1]].close();
//                        }
                        for(i=0;i<n;i++) {
                            System.out.println("ta["+i+"] = "+ta[i]);
                        }
                        System.out.println("k = "+k);
                        eor=false;
                        i = 0; mx = 0; min = Integer.parseInt(firstY[ta[0]]); //f[ta[0]].first;
                        System.out.println("in file T"+(i+1)+" min = "+min);
                        while (i < k) {
                            i = i + 1; x = Integer.parseInt(firstY[ta[i]]);//????
                            System.out.println("in file T"+(i+1)+" x = "+x);
                            if (x < min) {
                                min = x; mx = i;
                                System.out.println("New min = "+min);
                            }
                        }
                        System.out.println("Final min is "+min);
//                        copy(f[ta[mx]],f[t[n-1]]);
                        System.out.println("mx = "+mx+"; file with min value T"+(ta[mx]+1));
//                        firstX=readers[ta[mx]].readLine();
//                        firstZ=firstX;
                        in[t[n-1]].write(min+"\n");
                        System.out.println(min+" wrote to T4");
//                        firstY[mx]= String.valueOf(min);
                        firstY[ta[mx]]= readers[ta[mx]].readLine();
                        System.out.println("New arr of firstY:");
                        for(i=0;i<n;i++) {
                            System.out.println("firstY["+i+"] = "+firstY[i]);
                        }
//                        eor=false;
                        if(firstY[ta[mx]]!=null) {
                            System.out.println("mx = "+mx);
                            if (min > Integer.parseInt(firstY[ta[mx]])) {
                                eor = true;
//                                firstY[ta[mx]]= String.valueOf(MAX_VALUE);
                            }
                        }
                        else {
                            firstY[ta[mx]]= String.valueOf(MAX_VALUE);
                            eor=true;
                        }
                        System.out.println(eor);
//нужно как-то  сохранять значения, а когда нужно менять
                        if (eor) {
                            System.out.println("Seria stopped!");
                            ta[mx]=ta[k];
//                            for (int tx = mx; tx < k; tx++) {
//                                ta[tx] = ta[tx + 1];
//                            }
                            k = k - 1;
                        }
                        System.out.println();
                    } while (k != 0);
                }
                System.out.println("k != "+k);
                z = z - 1;
                System.out.println("z = "+z);
                System.out.println();
                System.out.println();
            }while (z!=0);
            tn=t[n-1];dn=d[n-1];z=a[n-2];
            for (i=n-1;i>0;i--) {
                t[i]=t[i-1];d[i]=d[i-1];a[i]=a[i-1]-z;
            }
            t[0]=tn;d[0]=dn;a[0]=z;
//            in[t[0]].write(t[n-1]);
            l=l-1;
            in[close].close();
//            Scanner sc = new Scanner(System.in);
//            sc.nextInt();
//            for(i=0;i<n;i++) {
//                System.out.println("t["+i+"] = "+t[i]);
//            }
            readers[t[n-1]].close();
            readers[close] = Files.newBufferedReader(f[close].toPath(), StandardCharsets.UTF_8);
            firstY[close] = readers[close].readLine();
            close=t[n-1];
            System.out.println("New arr of firstY:");
            for(i=0;i<n;i++) {
                System.out.println("firstY["+i+"] = "+firstY[i]);
            }

//            readers[close].close();
//            readers[close] = Files.newBufferedReader(f[close].toPath(), StandardCharsets.UTF_8);
//            for(i=0;i<=n-1;i++) {//!!!
////            readers[i].close();
//                in[i].close();
//            }
        } while (l!=0);
        System.out.println("I`m here!");


//        for(i=0;i<=n-1;i++) {//!!!
////            readers[i].close();
//            in[i].close();
//        }
        in[n-1].close();
        System.out.println("Sorted in file T"+(t[0]+1));
    }
}