package zktest;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestZK {

    @Test
    public void ls(){
        try {
            ZooKeeper zk = new ZooKeeper("master:2181,slave1:2181,slave2:2181",5000,null);
            List<String> list =  zk.getChildren("/",null);

            for(String s : list){
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }

    @Test
    //递归输出节点
    public void lsr(){
        try {
            ZooKeeper zk = new ZooKeeper("master:2181",5000,null);
            digui("/",zk);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
    public void digui(String path,ZooKeeper zk) throws KeeperException, InterruptedException {
        List<String> list =  zk.getChildren(path,null);

        for(String s : list){
            //加上父节点
            if(path.equals("/")) s= "/" + s;
            else s= path + "/" + s;
            System.out.println(s);
            //此节点存在子节点递归调用
            if(zk.getChildren(s,null) != null){
                digui(s,zk);
            }
        }
    }

    /**
     * 设置数据
     */
    @Test
    public void setData() throws Exception {
        ZooKeeper zk = new ZooKeeper("master:2181",5000,null);
        zk.setData("/a","zjx".getBytes(),0);
    }


    /**
     * 创建节点
     */
    @Test
    public void creareEmphoral() throws Exception{
        ZooKeeper zk = new ZooKeeper("master:2181",5000,null);
        zk.create("/b/b1","tomb1".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
    }

    /**
     *观察者
     */
    @Test
    public void watchTest() throws Exception {
        final ZooKeeper zk = new ZooKeeper("master:2181",5000,null);

        Stat stat = new Stat();
        Watcher w = null;
        w = new Watcher() {
            //回调，当数据改变时回调这个函数
            public void process(WatchedEvent watchedEvent) {
                try {
                    System.out.println("数据改了！！！");
                    zk.getData("/a",this,null);     //重新注册Watcher
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        byte[] data = zk.getData("/a",w,stat);

        System.out.println(new String(data));

        //使程序不停止，以接收watch信息
        while (true){
            Thread.sleep(1000);
        }
    }
}
