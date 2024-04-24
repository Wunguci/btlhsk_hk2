package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiSanPham;
import entity.SanPham;

public class ChiTietHoaDon_DAO {
	public ChiTietHoaDon_DAO() {

	}

	public ArrayList<ChiTietHoaDon> getAll_CTHD() {
		ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM ChiTietHoaDon";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				ChiTietHoaDon cthd = new ChiTietHoaDon();
				cthd.setSoLuong(rs.getInt("soLuong"));
				cthd.setThanhTien(rs.getDouble("thanhTien"));
				cthd.setTienGiam(rs.getDouble("tienGiam"));
				cthd.setHoaDon(new HoaDon(rs.getString("maHoaDon")));
				cthd.setSanPham(new SanPham(rs.getString("maSP")));
				dsCTHD.add(cthd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCTHD;
	}

	public boolean create(ChiTietHoaDon cthd) {
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			Statement st = con.createStatement();
			String sql = "INSERT INTO ChiTietHoaDon VALUES ('" + cthd.getSoLuong() + "', '" + cthd.getThanhTien()
					+ "', '"
					+ cthd.getTienGiam() + "', '" + cthd.getHoaDon().getMaHoaDon() + "', '"
					+ cthd.getSanPham().getMaSP() + "')";
			int rs = st.executeUpdate(sql);
			if (rs == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
