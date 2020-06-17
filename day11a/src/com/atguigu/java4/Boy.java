package com.atguigu.java4;

/**
 * @create 2020-06-07 15:10
 **/
public class Boy
{
    Girl girl;

    public Boy(Girl girl)
    {
        this.girl = girl;
    }

    public Boy()
    {
    }

    public Girl getGirl()
    {
        return girl;
    }

    public void setGirl(Girl girl)
    {
        this.girl = girl;
    }

    @Override
    public String toString()
    {
        return "Boy{" +
                "girl=" + girl +
                '}';
    }
}
