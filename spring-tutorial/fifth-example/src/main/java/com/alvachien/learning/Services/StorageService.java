package com.alvachien.learning.Services;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.alvachien.learning.Models.*;
import com.alvachien.learning.Utils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class StorageService {
	@Autowired
	JdbcTemplate jdbcTemplate;

	// Albums
	public Album getAlbumById(int id) {
		LogUtil.logger.info("Entering StorageService getAlbumById");

		return jdbcTemplate.execute((Connection conn) -> {
			try (var ps = conn.prepareStatement("SELECT * FROM Album WHERE id = ?")) {
				ps.setObject(1, id);
				try (var rs = ps.executeQuery()) {
					if (rs.next()) {
						Album nalbum = new Album();
						nalbum.loadDBResult(rs);
						return nalbum;
					}
					throw new RuntimeException("user not found by id.");
				}
			}
		});
	}

	public List<Album> getAlbums(int pageIndex) {
		LogUtil.logger.info("Entering StorageService getAlbums");

		int limit = 100;
		int offset = limit * (pageIndex - 1);
		return jdbcTemplate.query("SELECT AlbumID as Id, Title, Desp, IsPublic, CreateAt FROM Album ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY", new Object[] { offset, limit  },
			new BeanPropertyRowMapper<>(Album.class));
	}
	
	@Transactional(rollbackFor = {RuntimeException.class, IOException.class})
	public void createAlbum(Album nalbum) {

	}
	
	public List<Photo> getPhotos(int pageIndex) {
		LogUtil.logger.info("Entering StorageService getPhotos");

		int limit = 100;
		int offset = limit * (pageIndex - 1);
		return jdbcTemplate.query("SELECT PhotoID as id, Title as title, Desp as desp FROM Photo ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY", new Object[] { offset, limit  },
			new BeanPropertyRowMapper<>(Photo.class));
	}	
}
