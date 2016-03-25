package com.jean.dao.impl;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.jean.CustomDfmException;
import com.jean.DaoDfmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jean.dao.BaitDao;
import com.jean.entity.Bait;
import com.jean.entity.BaitType;
import org.springframework.stereotype.Repository;

@Repository
public class BaitDaoImpl extends BaseDaoImpl implements BaitDao {

    private static Logger log = LoggerFactory.getLogger(BaitDaoImpl.class);

    @Override
    public List<Bait> getBaitsToFishesByDate(int fishId, Date date) throws DaoDfmException {

	String sql =

	"SELECT b.bait_id, b.bait_name, bt.bait_type_name, bt.bait_type_id, b.description " + "FROM " + "baits AS b " + "INNER JOIN"
		+ " bait_types AS bt ON bt.bait_type_id = b.bait_type_id " + "INNER JOIN " + "baits_to_fishes AS btf ON btf.bait_id = b.bait_id "
		+ "INNER JOIN " + "baits_to_seasons AS bts ON bts.bait_id = b.bait_id "
		+ "WHERE ? BETWEEN bts.start_period AND bts.end_period AND btf.fish_id = ?";

	List<Bait> baits = new ArrayList<>();

	log.info("Starting method getBaitsForFishByDate(), with parameter values: [ fishId: " + fishId + ", date: " + date + " ]");

	try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {

	    preparedStatement.setDate(1, date);
	    preparedStatement.setInt(2, fishId);

	    ResultSet rs = preparedStatement.executeQuery();

	    while (rs.next()) {
		baits.add(getBaitFromRs(rs));
	    }

	    if (baits.isEmpty()) {
		throw new DaoDfmException("For some reason list of baits is empty");
	    }

	} catch (SQLException e) {
	    throw new DaoDfmException("Some problem with fetching list of baits " + "Message: " + e.getMessage(), e);
	}

	log.info("End method getBaitColors(), list size is: " + baits.size());

	return baits;
    }

    @Override
    public Integer saveBait(Bait bait) throws DaoDfmException, CustomDfmException {

	String sqlInsertBait = "INSERT INTO baits (bait_name, bait_type_id, description) VALUES (?, ?, ?)";

	Connection connection = getConnection();

	PreparedStatement statement = null;

	int primaryKey;

	try {

	    statement = connection.prepareStatement(sqlInsertBait, Statement.RETURN_GENERATED_KEYS);
	    statement.setString(1, bait.getName());
	    statement.setInt(2, bait.getBaitType().getTypeId());
	    statement.setString(3, bait.getDescription());
	    statement.executeUpdate();

	    ResultSet keySet = statement.getGeneratedKeys();
	    keySet.next();
	    primaryKey = keySet.getInt(1);

	    connection.commit();

	} catch (SQLException e) {
	    throw new DaoDfmException("Some problem with save bait. " + "Message: " + e.getMessage(), e);
	}

	return primaryKey;
    }

    public List<Bait> getBaitListToFish(int id) throws DaoDfmException {

	String sql = "";

	try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {

	    preparedStatement.setInt(1, id);

	    ResultSet rs = preparedStatement.executeQuery();

	    List<Bait> list = new ArrayList<>();

	    while (rs.next()) {
		list.add(getBaitFromRs(rs));
	    }

	    return list;

	} catch (SQLException e) {
	    throw new DaoDfmException("Some problem with save message text. " + "Message: " + e.getMessage(), e);
	}

    }

    @Override
    public Bait getBait(int baitId) throws DaoDfmException, CustomDfmException {

	String sql =

	"SELECT b.bait_id, b.bait_name, bt.bait_type_id, bt.bait_type_name, b.description " + "FROM " + "baits AS b " + "INNER JOIN "
		+ "bait_types AS bt ON b.bait_type_id = bt.bait_type_id " + "WHERE b.bait_id = ?";

	Bait bait = null;

	try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {

	    preparedStatement.setInt(1, baitId);
	    ResultSet rs = preparedStatement.executeQuery();
	    rs.next();
	    bait = getBaitFromRs(rs);

	} catch (SQLException e) {
	    throw new DaoDfmException("Some problem with fetching bait: " + baitId + "Message: " + e.getMessage());
	}
	return bait;

    }

    @Override
    public List<Bait> getBaitsToFishes(int fishId) throws DaoDfmException {

	String sql =

	"SELECT b.bait_id, b.bait_name, bt.bait_type_id, bt.bait_type_name, b.description " + "FROM " + "baits AS b " + "INNER JOIN "
		+ "bait_types AS bt ON b.bait_type_id = bt.bait_type_id " + "INNER JOIN " + "baits_to_fishes AS bts ON bts.bait_id = b.bait_id "
		+ "WHERE bts.fish_id = ?";

	List<Bait> baits = new ArrayList<Bait>();

	try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {

	    preparedStatement.setInt(1, fishId);
	    ResultSet rs = preparedStatement.executeQuery();
	    while (rs.next()) {
		baits.add(getBaitFromRs(rs));
	    }

	    if (baits.isEmpty()) {
		throw new DaoDfmException("For some reason list of baits is empty");
	    }

	} catch (SQLException e) {
	    throw new DaoDfmException("Some problem with fetching list of baits " + "Message: " + e.getMessage(), e);
	}

	log.info("End method getBaitColors(), list size is: " + baits.size());

	return baits;
    }

    @Override
    public List<Bait> getBaitsBySeason(Date date) throws DaoDfmException {
	String sql =

	"SELECT b.bait_id, b.bait_name, bt.bait_type_name, bt.bait_type_id, b.description " + "FROM " + "baits AS b " + "INNER JOIN"
		+ " bait_types AS bt ON bt.bait_type_id = b.bait_type_id " + "INNER JOIN " + "baits_to_seasons AS bts ON bts.bait_id = b.bait_id "
		+ "WHERE ? BETWEEN bts.start_period AND bts.end_period";

	List<Bait> baits = new ArrayList<>();

	try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {

	    preparedStatement.setDate(1, date);

	    ResultSet rs = preparedStatement.executeQuery();

	    while (rs.next()) {
		baits.add(getBaitFromRs(rs));
	    }

	    if (baits.isEmpty()) {
		throw new DaoDfmException("For some reason list of baits is empty");
	    }

	} catch (SQLException e) {
	    throw new DaoDfmException("Some problem with fetching list of baits " + "Message: " + e.getMessage(), e);
	}

	log.info("End method getBaitColors(), list size is: " + baits.size());

	return baits;
    }

    @Override
    public List<Bait> getBaits() throws DaoDfmException, CustomDfmException {

	String sql =

	"SELECT b.bait_id, b.bait_name, bt.bait_type_id, bt.bait_type_name, b.description " + "FROM " + "baits AS b " + "INNER JOIN "
		+ "bait_types AS bt ON b.bait_type_id = bt.bait_type_id ";

	List<Bait> baits = new ArrayList<>();

	try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {

	    ResultSet rs = preparedStatement.executeQuery();
	    while (rs.next()) {
		baits.add(getBaitFromRs(rs));
	    }

	    if (baits.isEmpty()) {
		throw new CustomDfmException("For some reason list of baits is empty");
	    }

	} catch (SQLException e) {
	    throw new DaoDfmException("Some problem with fetching list of baits " + "Message: " + e.getMessage(), e);
	}

	return baits;
    }

    @Override
    public void updateBait(Bait bait) throws DaoDfmException, CustomDfmException {

	String sql = "UPDATE baits AS b SET b.bait_name = ?, b.bait_type_id = ?, b.description = ? WHERE b.bait_id = ?";
	Connection connection = getConnection();
	int result;

	try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	    preparedStatement.setString(1, bait.getName());
	    preparedStatement.setInt(2, bait.getBaitType().getTypeId());
	    preparedStatement.setString(3, bait.getDescription());
	    preparedStatement.setInt(4, bait.getId());

	    result = preparedStatement.executeUpdate();

	    if (result == 0) {
		throw new CustomDfmException("Update didn't comlete: " + bait.toString());
	    }

	    connection.commit();

	} catch (SQLException e) {
	    throw new DaoDfmException("SQLEror: " + bait.toString() + e.getMessage());
	}
    }

    @Override
    public void deleteBait(int baitId) throws DaoDfmException, CustomDfmException {

	String sqlDeleteBait = "DELETE FROM baits WHERE bait_id = ?";
	String sqlDeleteBinding = "DELETE FROM baits_to_fishes WHERE bait_id = ?";
	String sqlDeleteSeason = "DELETE FROM baits_to_seasons WHERE bait_id = ?";

	Connection connection = getConnection();
	PreparedStatement statement = null;
	int result;

	try {

	    statement = connection.prepareStatement(sqlDeleteBinding);
	    statement.setInt(1, baitId);
	    statement.executeUpdate();

	    statement = connection.prepareStatement(sqlDeleteSeason);
	    statement.setInt(1, baitId);
	    statement.executeUpdate();

	    statement = connection.prepareStatement(sqlDeleteBait);
	    statement.setInt(1, baitId);
	    result = statement.executeUpdate();

	    if (result == 0) {
		throw new CustomDfmException("Delete didn't comlete with baitId: " + baitId);
	    }

	    connection.commit();

	} catch (SQLException e) {
	    throw new DaoDfmException("SQLEror: " + baitId + e.getMessage());
	}

    }

    private Bait getBaitFromRs(ResultSet rs) throws SQLException {
	Bait bait = new Bait();
	bait.setId(rs.getInt("bait_id"));
	bait.setBaitType(new BaitType(rs.getInt("bt.bait_type_id"), rs.getString("bt.bait_type_name")));
	bait.setName(rs.getString("bait_name"));
	bait.setDescription(rs.getString("description"));
	return bait;
    }

}
