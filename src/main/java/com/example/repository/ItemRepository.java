package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

/**
 * 商品情報を操作するリポジトリー
 * 
 * @author seiji_kitahara
 *
 */
@Repository
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private final static RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {

		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		item.setDeleted(rs.getBoolean("deleted"));
		return item;
	};

	/**
	 * 全商品情報を検索する.
	 * 
	 * @return 全商品情報が詰まったリスト
	 */
	public List<Item> load() {
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items ORDER BY price_m;";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 商品IDで主キー検索する
	 * 
	 * @param 主キー検索対象となる商品ID
	 * @return 検索結果
	 */
	public Item findByItemId(Integer itemId) {
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE id = :itemId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", itemId);
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER) ;
		if(itemList.size() == 0) {
			return null;
		}
		return itemList.get(0);
	}
	
	/**
	 * 曖昧検索で商品情報を取得する.
	 * 
	 * @param 検索時に入力した文字
	 * @return 検索でヒットした商品情報。無ければNullを返す。
	 */
	public List<Item> findByName(String keyword){
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE name ILIKE :name ORDER BY price_m;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + keyword + "%");
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER) ;
		if(itemList.size() == 0) {
			return null;
		}
		return itemList;
	}
}
