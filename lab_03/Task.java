public class Task{

    int _time,_priority;

    Task next;
    
    public Task(int priority,int time){
        _time=time;
        _priority=priority;
        next=null;
    }

    public int getPriority(){
        return _priority;
    }

    public int getTime(){
        return _time;
    }

    public Task copy (){
        return new Task(_priority, _time);
    }
}