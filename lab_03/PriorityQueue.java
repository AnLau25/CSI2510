public class PriorityQueue extends Queue{

    Queue pri0;
    Queue pri1;
    Queue pri2;

     public PriorityQueue(){
        pri0=new Queue();
        pri1=new Queue();
        pri2=new Queue();
    }

    public PriorityQueue(Queue _p0, Queue _p1, Queue _p2){
        pri0=_p0;
        pri1=_p1;
        pri2=_p2;
    }
    
    public void initFromTasks (Queue tasks){
        while (!tasks.isEmpty()){
            Task n= tasks.head;
            switch (n.getPriority()){
                case 0:
                    pri0.add(tasks.remove());
                    break;
                case 1:
                    pri1.add(tasks.remove());
                    break;
                case 2:
                    pri2.add(tasks.remove());
                    break;
            }
        }
    }

    public boolean isEmpty(){
        return pri0.isEmpty()&&pri1.isEmpty()&&pri2.isEmpty();
    }

    public Task getTask(){
        if (!pri0.isEmpty()){
            return pri0.remove();
        }else if(!pri1.isEmpty()){
            return pri1.remove();
        }else if(!pri2.isEmpty()){
            return pri2.remove();
        }else{
            EmptyQueueException e = new EmptyQueueException("All queues empty");
        }
        Task n=null;
        return  n;
    }

}
