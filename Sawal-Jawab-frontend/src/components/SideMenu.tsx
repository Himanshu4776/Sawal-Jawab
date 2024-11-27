import React, { useState, useEffect } from 'react';
import { Menu, X } from 'lucide-react';

interface SideMenuProps {
  currentUser: {
    username: string;
    email: string;
  } | null;
  handleLogout: () => void;
  filterQuestions: (filter: string) => void;
  filterTrendingQuestions: (filter: string) => 'MouseEventHandler<HTMLButtonElement> | undefined';
}

export const SideMenu: React.FC<SideMenuProps> = ({ 
  currentUser, 
  handleLogout, 
  filterQuestions,
  filterTrendingQuestions
}) => {
  const [isOpen, setIsOpen] = useState(false);
  const [isMobile, setIsMobile] = useState(window.innerWidth <= 768);
  const [trending, setTrending] = useState<string[]>([]);

  // Handle responsive design
  useEffect(() => {
    const checkMobile = () => {
      setIsMobile(window.innerWidth <= 768);
    };

    // Add resize listener
    window.addEventListener('resize', checkMobile);

    // Prevent body scroll when mobile menu is open
    if (isOpen && isMobile) {
      document.body.style.overflow = 'hidden';
    } else {
      document.body.style.overflow = 'unset';
    }

    // Cleanup
    return () => {
      window.removeEventListener('resize', checkMobile);
      document.body.style.overflow = 'unset';
    };
  }, [isOpen, isMobile]);

  const menuItems = [
    { label: 'All Questions', action: () => {
      filterQuestions('all');
      setIsOpen(false);
    }},
    { label: 'My Questions', action: () => {
      filterQuestions('my');
      setIsOpen(false);
    }},
    { label: 'Most Voted', action: () => {
      filterQuestions('voted');
      setIsOpen(false);
    }},
  ];

  const trendingList = ['dsa', 'cp', 'dev'];
  useEffect(() => {
    setTrending(trendingList);
  }, [])

  const toggleMenu = () => setIsOpen(!isOpen);

  return (
    <>
      {/* Mobile/Responsive Hamburger Button */}
      <button 
        onClick={toggleMenu}
        className="fixed top-8 left-2 z-50 md:hidden"
      >
        {isOpen ? <X size={24} /> : <Menu size={24} />}
      </button>

      {/* Side Menu Overlay for Mobile */}
      {isOpen && isMobile && (
        <div 
          className="fixed inset-0 bg-black/50 z-40"
          onClick={toggleMenu}
        />
      )}

      {/* Side Menu */}
      <div 
        className={`
          fixed top-0 left-0 h-inherit w-64 bg-white shadow-lg 
          transform transition-transform duration-300 ease-in-out
          z-50 overflow-y-auto
          ${isOpen && isMobile ? 'translate-x-0' : '-translate-x-full'}
          md:translate-x-0 md:relative md:block
        `}
      >
        <div className="p-4">
          {currentUser && (
            <div className="mb-4 border-b pb-4">
              <p className="text-lg font-semibold">{currentUser.username}</p>
              <p className="text-sm text-gray-500">{currentUser.email}</p>
            </div>
          )}

          <nav className="space-y-2">
            {menuItems.map((item, index) => (
              <button
                key={index}
                onClick={item.action}
                className="w-full text-left p-2 hover:bg-gray-100 rounded"
              >
                {item.label}
              </button>
            ))}

            {currentUser && (
              <button
                onClick={() => {
                  handleLogout();
                  setIsOpen(false);
                }}
                className="w-full text-left p-2 hover:bg-gray-100 rounded text-red-500"
              >
                Logout
              </button>
            )}
          </nav>
          <div className="mb-4 border-b pb-4"></div>
          <h2 className='font-bold text-lg'>Trending tags</h2>
          <div className="mb-4 border-b pb-4"></div>
          {trending.map((item, index) => (
              <button
                key={index}
                onClick={filterTrendingQuestions(item)}
                className="w-full text-left p-2 hover:bg-gray-100 rounded"
              >
                {item}
              </button>
            ))}

        </div>
      </div>
    </>
  );
};