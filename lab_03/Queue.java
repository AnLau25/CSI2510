import java.util.*;

public class Queue{

    Task head;

    public void add(Task task){

        if (head==null){
            head=task;
        }else{
            Task last=head;
            while (last.next!=null){
                last=last.next;
            }
            last.next=task;
        }
    }

    public Task remove(){
        if (head==null){
            EmptyQueueException e = new EmptyQueueException("All queues empty");
        }    
        Task n=head;
        head=head.next;
        return n.copy();
    } 

    public boolean isEmpty(){
        return (head==null);
    }  
}