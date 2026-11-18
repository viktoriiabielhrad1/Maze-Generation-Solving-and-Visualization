public class QueueArrayBased implements QueueInterface {
  private int maxQueue = 50; // maximum size of queue
  private Object[] items;
  private int front, back, count;
  
  public QueueArrayBased() {
	this(50);
    items = new Object[maxQueue];  

  }  // end default constructor
  public QueueArrayBased(int size) {
	  maxQueue = size;
	  items = new Object[size];  
	  front = 0;
	  back = size-1; 
	  count = 0;
  }

  // queue operations:
  public boolean isEmpty() {
    return count == 0;
  }  // end isEmpty

  public boolean isFull() {
    return count == maxQueue;
  }  // end isFull
  
  public void enqueue(Object newItem) {
    if (!isFull()) {
      back = (back+1) % (maxQueue);
      items[back] = newItem;
      ++count;
    }
    else {
      throw new QueueException("QueueException on enqueue: "
                             + "Queue full");
    }  // end if
  }  // end enqueue

  public Object dequeue() throws QueueException {
    if (!isEmpty()) {
      // queue is not empty; remove front
      Object queueFront = items[front];
      front = (front+1) % (maxQueue);
      --count;
      return queueFront;
    }
    else {
      throw new QueueException("QueueException on dequeue: "+
                              "Queue empty");
    }   // end if
  }  // end dequeue

  public void dequeueAll() {
    items = new Object[maxQueue];  
    front = 0;
    back = maxQueue-1;
    count = 0;
  }  // end dequeueAll

  public Object peek() throws QueueException {
    if (!isEmpty()) {  
      // queue is not empty; retrieve front
      return items[front];
    }
    else {
      throw new QueueException("Queue exception on peek: " + 
                              "Queue empty");
    }  // end if
  }  // end peek
  
  public void displayQueue() {
	  if (isEmpty()) {
		  System.out.println("The queue is empty");
	  }
	  else {
		 System.out.print("Front-> ");
		   for (int i = 0; i < count; i++) {
			   int index = (front + i) %(maxQueue);
			   System.out.print(items[index]);	   		   
		   }//end of for
		   System.out.println(" <-Back");
	  }
  }
   
} // end QueueArrayBased