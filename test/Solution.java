public class Solution {

    static int N;
    static int[] weak_a;
    static int[] dist_a;
    static int min_people;
    static int visit_count = 0;
    static boolean visited[];

    public static int solution(int n, int[] weak, int[] dist) {
        int answer = 0;

        N = n;
        weak_a = weak;
        dist_a = dist;

        visited = new boolean[weak.length];

        min_people = dist.length+1;




        return -1;
    }



    //모든 weak 지점 방문 끝났는지 체크
    public static boolean isCompleteVisit()
    {
        if(visit_count == visited.length)
            return true;
        return false;
    }


    public static int clockVisit(int start, int distance)
    {
        int current = start+1;
        int former = start;

        if(start == weak_a.length - 1)
            current = 0;

        int count = 0;

        setVisit(start);

        while(true)
        {
            int move = weak_a[current] - weak_a[former];

            if(move < 0)
                move += N;

            if(distance - move >= 0 && !visited[current])
            {
                distance -= move;
                setVisit(current);
                count++;

                if(current == weak_a.length-1)
                    current = 0;
                else
                    current++;

                if(former == weak_a.length-1)
                    former = 0;
                else
                    former++;
            }
            else
                break;
        }

        return count;
    }

    public static int clockUnvisit(int start, int distance)
    {
        int current = start+1;
        int former = start;

        if(start == weak_a.length - 1)
            current = 0;

        int count = 0;

        setUnvisit(start);

        while(true)
        {
            int move = weak_a[current] - weak_a[former];

            if(move < 0)
                move += N;

            if(distance - move >= 0 && visited[current])
            {
                distance -= move;
                setUnvisit(current);
                count++;

                if(current == weak_a.length-1)
                    current = 0;
                else
                    current++;

                if(former == weak_a.length-1)
                    former = 0;
                else
                    former++;
            }
            else
                break;
        }

        return count;
    }

    //반시계방향
    public static int counterClockVisit(int start, int distance)
    {
        int current = start-1;
        int former = start;

        if(start == 0)
            current = weak_a.length-1;

        int count = 0;

        setVisit(start);

        while(true)
        {
            int move = weak_a[former] - weak_a[current];

            if(move < 0)
                move += N;

            if(distance - move >= 0 && !visited[current])
            {
                distance -= move;
                setVisit(current);
                count++;

                if(current == 0)
                    current = weak_a.length-1;
                else
                    current--;

                if(former == 0)
                    former = weak_a.length-1;
                else
                    former--;
            }
            else
                break;
        }

        return count;
    }

    public static int counterClockUnvisit(int start, int distance)
    {
        int current = start-1;
        int former = start;

        if(start == 0)
            current = weak_a.length-1;

        int count = 0;

        setUnvisit(start);

        while(true)
        {
            int move = weak_a[former] - weak_a[current];

            if(move < 0)
                move += N;

            if(distance - move >= 0 && visited[current])
            {
                distance -= move;
                setUnvisit(current);
                count++;

                if(current == 0)
                    current = weak_a.length-1;
                else
                    current--;

                if(former == 0)
                    former = weak_a.length-1;
                else
                    former--;
            }
            else
                break;
        }

        return count;
    }

    //방문 처리
    public static void setVisit(int index)
    {
        if(!visited[index])
        {
            visited[index] = true;
            visit_count++;
        }
    }

    //방문 해제
    public static void setUnvisit(int index)
    {
        if(visited[index])
        {
            visited[index] = false;
            visit_count--;
        }
    }
}
