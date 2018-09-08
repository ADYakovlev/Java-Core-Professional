package Task03.model;

/*
 *@author Yakovlev Alexandr
 */
public class Student {
    private int id;
    private String FIO;
    private int Scope;

    public Student(int id, String FIO, int scope) {
        this.id = id;
        this.FIO = FIO;
        Scope = scope;
    }

    public Student(String FIO, int scope) {
        this.FIO = FIO;
        Scope = scope;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getScope() {
        return Scope;
    }

    public void getScope(int scope) {
        Scope = scope;
    }

    @Override
    public String toString(){
        return "Student{"+
                "id="+
                ", FIO"+ FIO+'\''+
                ", Scope="+Scope+
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this==o)return true;
        if(o==null||getClass()!=o.getClass())return false;
        Student student = (Student)o;
        if(Scope!=student.Scope)return false;
        return FIO!=null ? FIO.equals(student.FIO):student.FIO;
//        return FIO!=null ? FIO.equals((student.FIO)) : student.FIO;
    }

}
