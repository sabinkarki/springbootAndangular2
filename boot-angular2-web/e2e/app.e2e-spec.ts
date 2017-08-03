import { BootAngular2WebPage } from './app.po';

describe('boot-angular2-web App', () => {
  let page: BootAngular2WebPage;

  beforeEach(() => {
    page = new BootAngular2WebPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
