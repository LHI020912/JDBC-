package model;

public interface IBooksaleDAO {
	// Create
	public boolean create(BooksaleDTO dto) throws Exception;
	// Read
	public boolean read(BooksaleDTO dto) throws Exception;
	// Update
	public boolean update(BooksaleDTO dto) throws Exception;
	// Delete
	public boolean delete(String bsNo) throws Exception;
	// Stock
	public boolean stock(BooksaleDTO dto) throws Exception; 
	// StockUpdate
	public boolean stockUpdate(String bookNo, int diffQty) throws Exception;
	// StockRestore
	public boolean stockRestore(String bookNo, int qty) throws Exception;
}
