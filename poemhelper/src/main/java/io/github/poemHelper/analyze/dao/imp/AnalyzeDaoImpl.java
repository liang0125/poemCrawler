package io.github.poemHelper.analyze.dao.imp;

import io.github.poemHelper.analyze.dao.AnalyzeDao;
import io.github.poemHelper.analyze.entity.PoetryInfo;
import io.github.poemHelper.analyze.entity.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:lorrie
 * Create:2019/4/6
 */
public class AnalyzeDaoImpl implements AnalyzeDao {

    private final Logger logger = LoggerFactory.getLogger(AnalyzeDaoImpl.class);

    private final DataSource dataSource;

    public AnalyzeDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Result> analyzeResult() {
        List<Result> datas = new ArrayList<>();
        //try()自动关闭
        String sql = "select count(*) as count , author from poetry_info group by author;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()
        ) {
            while (rs.next()){
                Result authorCount = new Result();
                authorCount.setAuthor(rs.getString("author"));
                authorCount.setCount(rs.getInt("count"));
                datas.add(authorCount);
            }
        } catch (SQLException e) {
            logger.error("Database query occur exception {}.",e.getMessage());
        }
        return datas;
    }

    @Override
    public List<PoetryInfo> queryAllPoetryInfo() {
        List<PoetryInfo> datas = new ArrayList<>();
        String sql ="select title, dynasty , author, content from poetry_info;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()
        ){
            while (rs.next()){
                PoetryInfo poetryInfo = new PoetryInfo();
                poetryInfo.setTitle(rs.getString("title"));
                poetryInfo.setDynasty(rs.getString("dynasty"));
                poetryInfo.setAuthor(rs.getString("author"));
                poetryInfo.setContent(rs.getString("content"));
                datas.add(poetryInfo);
            }

        }catch (SQLException e){
            logger.error("Database query occur exception {}.",e.getMessage());
        }
        return datas;
    }
}
