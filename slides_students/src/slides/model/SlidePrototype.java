package slides.model;

import slides.layout.LayoutManager;

public class SlidePrototype implements Cloneable {
    private LayoutManager layout;

    public SlidePrototype(LayoutManager layout) {
        this.layout = layout;
    }

    public Slide clone() {
        return new Slide(layout);
    }
}
