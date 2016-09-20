package dropper.interfaces;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.List;

import dropper.model.*;

@SuppressWarnings("unused")
public class MyDragDropListener implements DropTargetListener {

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public void drop(DropTargetDropEvent event) {

        // Accept copy drops
        event.acceptDrop(DnDConstants.ACTION_COPY);

        // Get the transfer which can provide the dropped item data
        Transferable transferable = event.getTransferable();

        // Get the data formats of the dropped item
        DataFlavor[] flavors = transferable.getTransferDataFlavors();

        // Loop through the flavors
        for (DataFlavor flavor : flavors) {

            try {

                // If the drop items are files
                if (flavor.isFlavorJavaFileListType()) {

                    // Get all of the dropped files
                    List<File> files = (List) transferable.getTransferData(flavor);

                    // Loop them through
                    for (File file : files) {

                        // Print out the file path
                        System.out.println("File path is '" + file.getPath() + "'.");
                       // InsereBD ibd = new InsereBD(file);
                      //  Thread t1 = new Thread (new InsereBD(file,""));
                      //  t1.start();

                    }

                }

            } catch (Exception e) {

                // Print out the error stack
                e.printStackTrace();

            }
        }

        // Inform that the drop is complete
       event.dropComplete(true);
       
    }

    @Override
    public void dragEnter(DropTargetDragEvent event) {
    }

    @Override
    public void dragExit(DropTargetEvent event) {
    }

    @Override
    public void dragOver(DropTargetDragEvent event) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent event) {
    }

    
    public String getResultado(){
    	return "Arquivo Gravado com Sucesso!";
    }
    
    
}
