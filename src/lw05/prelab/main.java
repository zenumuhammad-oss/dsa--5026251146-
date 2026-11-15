package lw05.prelab;

import java.util.*;

class Main {
    static int[][] graph;
    static int size;
    static String[] names;
    static List<Integer> dfsOrder = new ArrayList<>();

    static void addEdge(int from, int to, int minutes) {
        graph[from][to] = minutes;
        graph[to][from] = minutes;
    }

    static void printPath(List<Integer> order) {
        for (int i = 0; i < order.size(); i++) {
            System.out.print(names[order.get(i)]);
            if (i < order.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }

    static List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);

            for (int i = 0; i < size; i++) {
                if (graph[current][i] != 0 && !visited.contains(i)) {
                    visited.add(i);
                    queue.add(i);
                }
            }
        }
        return order;
    }

    static void dfs(int current, Set<Integer> visited) {
        visited.add(current);
        dfsOrder.add(current);

        for (int i = 0; i < size; i++) {
            if (graph[current][i] != 0 && !visited.contains(i)) {
                dfs(i, visited);
            }
        }
    }

    static void dijkstraAlg(int start, int destination) {
        int[] dist = new int[size];
        int[] prev = new int[size];
        boolean[] visited = new boolean[size];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        for (int count = 0; count < size; count++) {
            int u = findTheNextNode(dist, visited);
            if (u == -1) break;
            visited[u] = true;

            for (int v = 0; v < size; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE 
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;
                }
            }
        }

        System.out.println("=== Shortest Time from " + names[start] + " ===");
        for (int i = 0; i < size; i++) {
            System.out.println(names[i] + " : " + dist[i] + " min");
        }

        System.out.println("=== Shortest Route from " + names[start] + " to " + names[destination] + " ===");
        route(prev, destination);
        System.out.println();
        System.out.println("Total time: " + dist[destination] + " min");
    }

    static int findTheNextNode(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < size; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    static void route(int[] prev, int current) {
        if (current == -1) return;
        if (prev[current] != -1) {
            route(prev, prev[current]);
            System.out.print(" -> ");
        }
        System.out.print(names[current]);
    }

    public static void main(String[] args) {
        Scanner zn = new Scanner(Main.class.getResourceAsStream("campus.txt"));

        if (!zn.hasNextInt()) {
            zn.close();
            return;
        }

        size = zn.nextInt();
        names = new String[size];
        for (int i = 0; i < size; i++) {
            names[i] = zn.next();
        }

        graph = new int[size][size];

        int edgesCount = zn.nextInt();
        for (int i = 0; i < edgesCount; i++) {
            int v1 = zn.nextInt();
            int v2 = zn.nextInt();
            int minutes = zn.nextInt();
            addEdge(v1, v2, minutes);
        }

        int start = zn.nextInt();
        int destination = zn.nextInt();
        zn.close();

        System.out.println("=== BFS from " + names[start] + " ===");
        printPath(bfs(start));

        System.out.println("=== DFS from " + names[start] + " ===");
        dfsOrder.clear();
        dfs(start, new HashSet<>());
        printPath(dfsOrder);

        dijkstraAlg(start, destination);
    }
}