package cn.ac.yhao.evaluation.json;

import cn.ac.yhao.evaluation.json.jsonUtils.FastJsonUtil;
import cn.ac.yhao.evaluation.json.jsonUtils.GsonUtil;
import cn.ac.yhao.evaluation.json.jsonUtils.JacksonUtil;
import cn.ac.yhao.evaluation.json.model.FullName;
import cn.ac.yhao.evaluation.json.model.Person;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @description: Json序列化测评
 * @author: Daniel Young
 * @create: 2021-08-05 10:17
 */
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS) //结果输出单位
@State(Scope.Benchmark)
public class JsonSerializeBenchmark {

    /**
     * 序列化次数参数
     */
    @Param({"1000", "10000", "100000"})
    private int count;

    private Person p;

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(JsonSerializeBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(0)
                .build();
        Collection<RunResult> results =  new Runner(opt).run();
        //ResultExporter.exportResult("JSON序列化性能", results, "count", "秒");
    }

    @Benchmark
    public void Gson(Blackhole bh) {
        for (int i = 0; i < count; i++) {
            bh.consume(GsonUtil.bean2Json(p));
        }
    }

    @Benchmark
    public void FastJson(Blackhole bh) {
        for (int i = 0; i < count; i++) {
            bh.consume(FastJsonUtil.bean2Json(p));
        }
    }

    @Benchmark
    public void Jackson(Blackhole bh) {
        for (int i = 0; i < count; i++) {
            bh.consume(JacksonUtil.bean2Json(p));
        }
    }

    @Setup
    public void prepare() {
        List<Person> friends=new ArrayList<Person>();
        friends.add(createAPerson("小明",null));
        friends.add(createAPerson("Tony",null));
        friends.add(createAPerson("陈小二",null));
        p=createAPerson("邵同学",friends);
    }

    @TearDown
    public void shutdown() {
    }

    private Person createAPerson(String name,List<Person> friends) {
        Person newPerson=new Person();
        newPerson.setName(name);
        newPerson.setFullName(new FullName("zjj_first", "zjj_middle", "zjj_last"));
        newPerson.setAge(24);
        List<String> hobbies=new ArrayList<String>();
        hobbies.add("篮球");
        hobbies.add("游泳");
        hobbies.add("coding");
        newPerson.setHobbies(hobbies);
        Map<String,String> clothes=new HashMap<String, String>();
        clothes.put("coat", "Nike");
        clothes.put("trousers", "adidas");
        clothes.put("shoes", "安踏");
        newPerson.setClothes(clothes);
        newPerson.setFriends(friends);
        return newPerson;
    }

}
