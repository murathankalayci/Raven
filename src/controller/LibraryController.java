package controller;

import model.Book;
import model.Library;
import view.LibraryView;
import model.Handler;

/**
 * Created by Batzuhan on 14/12/2017.
 */
public class LibraryController {
    private Library library;
    private LibraryView view;
    public static Handler handler = new Handler();
    public Boolean setVisible = false;

    public LibraryController(Library library) {
        this.library = library;
        this.view = new LibraryView(this);
    }

    public Library getLibrary() {
        return library;
    }

    public LibraryView getView() {
        return view;
    }

    public void addButtonClicked(String name, String author, int quantity) {
        view.textFieldPanel.removeAll();
        view.infoPanel.removeAll();
        handler.addBook(name, author, quantity, library);
        view.createTextFieldPanel();
        view.revalidate();
    }

    public void deleteButtonClicked(String name, String author, int quantity) {
        view.textFieldPanel.removeAll();
        view.infoPanel.removeAll();
        handler.deleteBook(name, author, quantity, library);
        view.createTextFieldPanel();
        view.revalidate();
    }

    public void showInfoButtonClicked(String name, String author) {
        view.textFieldPanel.removeAll();
        view.infoPanel.removeAll();
        view.createTextFieldPanel();
        if (!name.equals("")) {
            if (!author.equals("")) {
                view.createInfoPanel(handler.getBooksByNameAndAuthor(name, author, library));
            } else {
                view.createInfoPanel(handler.getBooksByName(name, library));
            }
        } else {
            view.createInfoPanel(handler.getBooksByAuthor(author, library));
        }
        view.revalidate();
    }

    public void listAllButtonClicked() {
        setVisible = !setVisible;
        view.infoPanel.removeAll();
        view.createInfoPanel(library.getBooks());
        view.infoPanel.setVisible(setVisible);
        view.revalidate();
    }
}
