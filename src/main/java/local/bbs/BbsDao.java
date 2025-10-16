package local.bbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BbsDao {
    private final JdbcTemplate jdbc;

    @Autowired
    public BbsDao(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public int insert(BbsItem item){
        String sql = "INSERT INTO bbs(id, posttime, name, article) VALUES(?, ?, ?, ?)";
        int cnt = jdbc.update(sql, item.id(), item.posttime(), item.name(), item.article());
        return cnt;
    }

    public List<BbsItem> getAll(){
        String sql = "SELECT id, posttime, name, article FROM bbs ORDER BY posttime DESC";
        List<Map<String, Object>> result = jdbc.queryForList(sql);
        List<BbsItem> list = result.stream()
            .map((Map<String, Object> row) -> new BbsItem(
                row.get("id").toString(),
                row.get("posttime").toString(),
                row.get("name").toString(),
                row.get("article").toString()
            )).toList();
        return list;
    }
}
