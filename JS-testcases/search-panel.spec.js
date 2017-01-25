import SearchPanelModule, { SearchPanelComponent } from './';

describe('Search Panel', () => {
  let ctrl, YoutubeSearch;

  beforeEach(window.module(SearchPanelModule));

  beforeEach(window.inject(($componentController) => {
    // spies
    YoutubeSearch = jasmine.createSpyObj('YoutubeSearch', ['resetPageToken', 'search']);
    YoutubeSearch.params = {};
    ctrl = $componentController(SearchPanelComponent.selector, {
      $http: {}, $q: {}, $window: {}, YoutubeSearch
    });
  }));

  it('should reset the page token when the query has changed', () => {
    ctrl.params.q = 'some random text ' + Date().toString();
    ctrl.resetPageToken();
    expect(YoutubeSearch.resetPageToken).toHaveBeenCalled();
    expect(YoutubeSearch.resetPageToken.calls.count()).toBe(1);
  });

  //CPEN422

  it('should call Youtube Search when search has been called has changed (CPEN 422)', () => {
    ctrl.search();
    expect(YoutubeSearch.search).toHaveBeenCalled();
    expect(YoutubeSearch.search.calls.count()).toBe(1);
  });


});
